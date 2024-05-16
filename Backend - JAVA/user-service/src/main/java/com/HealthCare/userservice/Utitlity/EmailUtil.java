package com.HealthCare.userservice.Utitlity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sentOtpEmail(String email, String otp) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Verify OTP");
        simpleMailMessage.setText("Hello , your OTP is "+otp);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");
        String emailContent = """
        <div>
            <p>Hello,Welcome to HealthCare</p>
            <p>Your OTP for verification is: %s</p>
            <p>Thank you!</p>
        </div>
        """.formatted(otp);
        mimeMessageHelper.setText(emailContent,true);
        javaMailSender.send(mimeMessage);
    }
}
