package com.example.cosmos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ResetPasswordDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
