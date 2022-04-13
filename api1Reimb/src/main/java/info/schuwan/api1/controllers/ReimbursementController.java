package info.schuwan.api1.controllers;

import info.schuwan.api1.dto.CreateReimbDTO;
import info.schuwan.api1.dto.UpdateStatusDTO;
import info.schuwan.api1.entities.ReimbStatus;
import info.schuwan.api1.entities.ReimbsUser;
import info.schuwan.api1.entities.Reimbursement;
import info.schuwan.api1.services.ReimbsUserService;
import info.schuwan.api1.services.ReimbursementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reimbs")
public class ReimbursementController {

    @Value("http://api2:7072/api2")
//@Value("http://localhost:7072/api2")
    String api2Url;


    private ReimbursementService reimbursementService;
    private ReimbsUserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementController.class);


    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService, ReimbsUserService userService) {
        this.reimbursementService = reimbursementService;
        this.userService = userService;
    }

    @Autowired                              //--------------------
    RestTemplate restTemplate;              //--------------------

    @GetMapping("/show-all")
    public ResponseEntity getAll() {
        logger.debug("Loading all reimbursements");
        List<Reimbursement> allReimbs = reimbursementService.getAllReimbursements();
        if(allReimbs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(allReimbs);
    }


    @PostMapping("/send-notification")                                          //--------------------
    public ResponseEntity<?> createNewResource(@RequestBody  String message) {      //--------------------
        System.out.println("API1>> we received a message.. \n\t\t " + message);     //--------------------
        ResponseEntity api1Response = restTemplate.postForEntity(api2Url, message, null);

        if(api1Response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }


    @GetMapping(path ="{id}/reimbs")
    public ResponseEntity getMine(@PathVariable("id") Long userId) {
        logger.debug("Loading user with id {}", userId);
        Optional<ReimbsUser> optUser = userService.getUserById(userId);

        if(!optUser.isPresent()) {
            logger.debug("User not found");
            return ResponseEntity.badRequest().body("User could not be found");
        }

        ReimbsUser user = optUser.get();
        logger.debug("Found user {}", user.getEmail());

        List<Reimbursement> userReimbs = reimbursementService.getAllForEmployee(user);

        if(userReimbs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(userReimbs);
    }


    @PostMapping(path = "/new-expense")
    public ResponseEntity postReimb(@RequestBody CreateReimbDTO reimb) {
        logger.debug("\t\tAdding expense with details follows>>>>>>\t\t{}", reimb);
        try {
            Optional<ReimbsUser> optSubmitter = userService.getUserByEmail(reimb.getEmpEmail());
            Optional<ReimbsUser> optApprover = userService.getUserByEmail(reimb.getManEmail());

            if(!optSubmitter.isPresent() || !optApprover.isPresent()) {
                return ResponseEntity.badRequest().body("Greeting,  " + optSubmitter.get().getFirstName() + optSubmitter.get().getLastName());
            }

            Reimbursement newReimb = reimbursementService.saveReimbursement(reimb, optSubmitter.get(), optApprover.get());
            transmittoApi2Email(newReimb.getEmployee().getEmail(),newReimb.getEmployee().getEmail(),newReimb.getId());         // send emails to employee and Manager
            return ResponseEntity.created(new URI(api2Url + newReimb.getId())).build();

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }


    @PatchMapping(path ="{id}/update-status")
    public ResponseEntity updateStatus(@PathVariable("id") Long reimbId, @RequestBody String decision) {
        UpdateStatusDTO newStatus =new UpdateStatusDTO(decision);
        try {
            Optional<Reimbursement> optReimb = reimbursementService.getById(reimbId);

            if(!optReimb.isPresent()) {
                return ResponseEntity.badRequest().body("Reimburse does not exist");
            }

            Reimbursement reimb = optReimb.get();
            reimb.setStatus(ReimbStatus.valueOf(newStatus.getNewStatus()));
            reimbursementService.saveReimbursement(reimb);
            transmittoApi2Email(reimb.getEmployee().getEmail(),reimb.getManager().getEmail(),reimbId);         // send emails to employee and Manager
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PatchMapping(path ="{id}/update-manager")
    public ResponseEntity updateManager(@PathVariable("id") Long reimbId, @RequestBody String manEmail) {

        Optional<ReimbsUser> optUser = userService.getUserByEmail(manEmail);
        if(!optUser.isPresent()) {
            logger.debug("User not found");
            return ResponseEntity.badRequest().body("This Manager's email is showing they are not among the active users, please contact your sys admin");
        }

        ReimbsUser user = optUser.get();
        logger.debug("Found user {}", user.getEmail());
        try {
            Optional<Reimbursement> optReimb = reimbursementService.getById(reimbId);
            if(!optReimb.isPresent()) {
                return ResponseEntity.badRequest().body("Reimburse does not exist");
            }
            Reimbursement reimb = optReimb.get();                                         //convert from optional to none
            reimb.setManager(user);
            reimbursementService.saveReimbursement(reimb);

            transmittoApi2Email(reimb.getEmployee().getEmail(),reimb.getManager().getEmail(),reimbId);         // send emails to employee and Manager
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @PostMapping("/send-emails")                                          //--------------------
    public ResponseEntity<?> transmittoApi2Email(String email1, String email2, Long reimbId) {      //--------------------
        String message = email1+","+email2+","+reimbId.toString();
        System.out.println(message);
        ResponseEntity api1Response = restTemplate.postForEntity(api2Url, message, null);
        if(api1Response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }
}
