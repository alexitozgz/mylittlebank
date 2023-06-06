package com.iobuilders.mylittebank.domain.services;

import com.iobuilders.mylittebank.domain.model.BankUser;

public interface UserService {
    BankUser registerUser(String name, String phoneNumber, String email);

    BankUser getUser(long id);
}
