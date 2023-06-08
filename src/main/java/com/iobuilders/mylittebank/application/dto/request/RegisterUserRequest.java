package com.iobuilders.mylittebank.application.dto.request;

import com.iobuilders.mylittebank.domain.model.Wallet;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class RegisterUserRequest {

    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
