package com.HealthCare.authservice.controller;

import com.HealthCare.authservice.dto.SignupRequest;
import com.HealthCare.authservice.entity.UserCredential;
import com.HealthCare.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class SignupController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        UserCredential user = authService.createUser(signupRequest);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @PostMapping("/{userId}/block")
    public ResponseEntity<?> blockUser(@PathVariable UUID userId) {
        authService.blockUser(userId);
        return ResponseEntity.ok("User blocked/unblocked successfully.");
    }


}
