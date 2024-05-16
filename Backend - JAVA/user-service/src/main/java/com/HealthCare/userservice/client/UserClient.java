package com.HealthCare.userservice.client;

import com.HealthCare.userservice.dto.DoctorDto;
import com.HealthCare.userservice.dto.LoginDetails;
import com.HealthCare.userservice.dto.PharmaDto;
import com.HealthCare.userservice.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface UserClient {
//    @PostExchange("/auth/signup")
//    public ResponseEntity<?> authsignup(@RequestBody LoginDetails loginDetails) ;

    @PostExchange("/auth/signup")
    public ResponseEntity<?> signupUser(@RequestBody LoginDetails loginDetails) ;
//
//    @PostMapping("/auth/doctor")
//    public ResponseEntity<?> signupDoctor(@RequestBody DoctorDto doctorDto);
//
//    @PostMapping("/auth/pharma")
//    public ResponseEntity<?> signupPharma(@RequestBody PharmaDto pharmaDto);
}
