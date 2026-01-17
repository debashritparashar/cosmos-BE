package com.example.cosmos.controller;

import com.example.cosmos.dto.ResetPasswordDto;
import com.example.cosmos.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/password")
public class PasswordResetController {

    private static final Logger log =
             LoggerFactory.getLogger(PasswordResetController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody ResetPasswordDto resetPasswordDto) throws Exception {
       log.info("Received Reset Password Request for Email: " + resetPasswordDto.getEmail() +
                " and Phone Number: " + resetPasswordDto.getPhoneNumber());
        emailService.sendOtpEmail(resetPasswordDto.getEmail(), resetPasswordDto.getPhoneNumber());
        return ResponseEntity.ok("OTP sent successfully");
    }
}
