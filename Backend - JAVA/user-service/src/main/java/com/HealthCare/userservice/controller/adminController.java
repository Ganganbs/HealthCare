package com.HealthCare.userservice.controller;

import com.HealthCare.userservice.entity.User;
import com.HealthCare.userservice.service.signup.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class adminController {

    @Autowired
    AdminService adminService;

        @GetMapping("/allpatient")
    public ResponseEntity<List<User>>getAllPatient(){
        return ResponseEntity.ok(adminService.getAllPatients());
    }

    @GetMapping("/patient/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable UUID userId) {
        Optional<User> optionalUser = adminService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
