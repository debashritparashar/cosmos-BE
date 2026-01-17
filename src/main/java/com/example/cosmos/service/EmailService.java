package com.example.cosmos.service;

import com.example.cosmos.controller.PasswordResetController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    private static final Logger log =
            LoggerFactory.getLogger(PasswordResetController.class);

    @Autowired
    private JavaMailSender mailSender;

    private static String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public void sendOtpEmail(String email,String phoneNumber)throws Exception {

        String otp = EmailService.generateOtp();

        log.info("Email Id: " + email);
        log.info("Phone Number: " + phoneNumber);
        log.info("Generated OTP: " + otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Verification Code");
        message.setText("Your verification code is: " + otp);

        mailSender.send(message);
    }
}
