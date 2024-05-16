package com.HealthCare.userservice.service.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, Instant> otpMap = new HashMap<>();
    public void sendSimpleEmail(String toEmail, String subject) {
        String otp = generateOTP();

        otpMap.put(otp, Instant.now());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fromemail@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText("Your OTP is: " + otp);

        // Send email
        mailSender.send(message);
        System.out.println("Mail Sent...");
    }

    // Method to generate a 6-digit OTP
    private String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }
    public boolean isOTPValid(String otp) {
        Instant creationTime = otpMap.get(otp);
        if (creationTime != null) {
            Instant currentTime = Instant.now();
            return currentTime.minusSeconds(300).isBefore(creationTime); //5 min
        }
        return false;
    }
}