package com.HealthCare.userservice.Utitlity;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    public String generateOtp(){
        int otpLength = 4;
        String allowedChars = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            int index = random.nextInt(allowedChars.length());
            otp.append(allowedChars.charAt(index));
        }
        return otp.toString();

    }

}
