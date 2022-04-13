package info.schuwan.api2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api2")
public class Api2Controller {
    @PostMapping
    public ResponseEntity acknowlegeReceipt(@RequestBody String message) {
        System.out.println("API2>> we Received the message back..through API  \n\t\t " );
        String[] messageComponents = message.split(",");
        String email1= messageComponents[0];
        String email2= messageComponents[1];
        String reimbId = messageComponents[2];
        System.out.println(email1 + email2 + reimbId);

        String mySubject1 = "You have submitted a request for expense reimbursement- request id: "+reimbId;
        String emailBody1 = "Dear "+ email1 + ",\n" +
                "A reimbursement request was submitted to your Manager."+
                "  You will receive an email as soon as it is approved.  Thank you!";
        String mySubject2 = "A reimbursement request was submitted- request id: "+reimbId;
        String emailBody2 = "Dear "+ email2 + ",\n" +
                "A reimbursement request was submitted for you to review and approve."+
                "  Please do so within 3 days. Thank you";
        new SendMailController(mySubject1, emailBody1, email1);
        new SendMailController(mySubject2, emailBody2, email2);

        // start a thread to send /relay the message somewhere else or trigger a workflow

        // return accepted
//        return ResponseEntity.accepted().build();
        return ResponseEntity.ok().body("all good");
    }
}
