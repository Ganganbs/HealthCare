package com.HealthCare.userservice.service.signup;

import com.HealthCare.userservice.dto.*;
import com.HealthCare.userservice.entity.Admin;
import com.HealthCare.userservice.entity.Doctor;
import com.HealthCare.userservice.entity.Pharma;
import com.HealthCare.userservice.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface SignupService {

    Admin createAdmin(AdminDto adminDto);

    User createUser(UserDto userDto) throws MessagingException;

    boolean verifyAccount(OtpDto otpDto);


//    Doctor createDoctor(DoctorDto doctorDto, MultipartFile image) throws MessagingException , IOException;

    Doctor createDoctor(DoctorDto doctorDto) throws MessagingException, IOException;

    Pharma createPharma(PharmaDto pharmaDto) throws MessagingException;


    User getUserByEmail(String email);
}
