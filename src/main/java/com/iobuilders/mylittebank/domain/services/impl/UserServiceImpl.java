package com.iobuilders.mylittebank.domain.services.impl;

import com.iobuilders.mylittebank.domain.model.User;
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
    public User registerUser(String name, String phoneNumber, String email) {
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        return null;
    }
}
