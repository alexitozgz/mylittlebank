package com.iobuilders.mylittebank.domain.service.impl;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.repository.UserRepository;
import com.iobuilders.mylittebank.domain.service.UserService;
import org.springframework.stereotype.Service;

//@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String name, String phoneNumber, String email) {
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        //userRepository.saveUser(user);
    }

    @Override
    public User getUser(long id) {
        return null;
    }
}
