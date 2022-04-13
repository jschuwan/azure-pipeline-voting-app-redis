//package com.example.reimbapi1.dto;
//
//import com.example.reimbapi1.entities.ReimbsUser;
//import com.example.reimbapi1.entities.repos.ReimbsUserRepository;
//import com.example.reimbapi1.services.ReimbsUserService;
//import lombok.NoArgsConstructor;
//
//import java.util.Optional;
//
//@NoArgsConstructor
//public class UpdateManagerDTO {
//    private ReimbsUser user;
//
//    ReimbsUserService usersvc;
//
//    public UpdateManagerDTO(String newManEmail) {
//        setUser(usersvc.getUserByEmail(newManEmail).get());
////        setUser(lookupUser.get());
//    }
//
//    public ReimbsUser getUser() {
//        return user;
//    }
//
//    public void setUser(ReimbsUser someuser) {
//        this.user = someuser;
////
//    }
//
//    @Override
//    public String toString() {
//        return "UpdateStatusDTO{" +
//                "newManager='" + user + '\'' +
//                '}';
//    }
//}
