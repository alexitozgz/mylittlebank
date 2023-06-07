package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.model.User;

public interface UserService {
    void registerUser(String name, String phoneNumber, String email);

    User getUser(long id);
}
