package com.iobuilders.mylittebank.domain.services;

import com.iobuilders.mylittebank.domain.model.User;

public interface UserService {
    User registerUser(String name, String phoneNumber, String email);

    User getUser(long id);
}
