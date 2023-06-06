package com.iobuilders.mylittebank.domain.services.impl;

import com.iobuilders.mylittebank.domain.model.BankUser;
import com.iobuilders.mylittebank.domain.services.UserService;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final JpaUserRepository userRepository;

    public UserServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BankUser registerUser(String name, String phoneNumber, String email) {
        BankUser bankUser = new BankUser();
        bankUser.setName(name);
        bankUser.setPhoneNumber(phoneNumber);
        bankUser.setEmail(email);
        return userRepository.save(bankUser);
    }

    @Override
    public BankUser getUser(long id) {
        return null;
    }
}
