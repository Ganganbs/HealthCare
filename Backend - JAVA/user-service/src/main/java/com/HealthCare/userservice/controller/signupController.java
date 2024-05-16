package com.HealthCare.userservice.controller;

import com.HealthCare.userservice.client.UserClient;
import com.HealthCare.userservice.dto.*;
import com.HealthCare.userservice.entity.Admin;
import com.HealthCare.userservice.entity.Doctor;
import com.HealthCare.userservice.entity.Pharma;
import com.HealthCare.userservice.entity.User;
import com.HealthCare.userservice.repository.UserRepository;
import com.HealthCare.userservice.service.signup.SignupService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.PostExchange;

import java.io.IOException;

@Controller
@RequestMapping("user")
public class signupController {
    @Autowired
    SignupService signupService;

    @Autowired
    UserClient userClient;

    @PostMapping("/signup/admin")
    public ResponseEntity<?> signupAdmin(@RequestBody AdminDto adminDto) {
        Admin user = signupService.createAdmin(adminDto);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @PostMapping("/signup/user")
    public ResponseEntity<?> signupUser(@RequestBody UserDto userDto) throws MessagingException {
        User user = signupService.createUser(userDto);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @PostMapping("/signup/doctor")
    public ResponseEntity<?> signupUser(@RequestBody DoctorDto doctorDto) throws MessagingException, IOException {
        Doctor doctor = signupService.createDoctor(doctorDto);
        if (doctor != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }


//    @PostMapping("/signup/doctor")
//    public ResponseEntity<?> signupUser(@RequestPart("doctorDto") DoctorDto doctorDto,
//                                        @RequestPart("image") MultipartFile image) throws MessagingException, IOException {
//        Doctor doctor = signupService.createDoctor(doctorDto,image);
//        if (doctor != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
//        }
//    }

    @PostMapping("/signup/pharma")
    public ResponseEntity<?> signupUser(@RequestBody PharmaDto pharmaDto) throws MessagingException {
        Pharma pharma = signupService.createPharma(pharmaDto);
        if (pharma != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pharma);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getAllUsers(String email){
        User users = signupService.getUserByEmail(email);
        System.out.println(users);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/verify-account")
    public ResponseEntity<ResponseDto> verifyAccount(@RequestBody OtpDto otpDto) {
        boolean verificationSuccess = signupService.verifyAccount(otpDto);
        ResponseDto response;
        if (verificationSuccess) {
            response = new ResponseDto(true, "Verification successful");
        } else {
            response = new ResponseDto(false, "Invalid OTP");
        }
        return ResponseEntity.ok(response);
    }




}
