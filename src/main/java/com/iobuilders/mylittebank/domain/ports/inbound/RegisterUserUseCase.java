package com.iobuilders.mylittebank.domain.ports.inbound;

import com.iobuilders.mylittebank.domain.model.User;

public interface RegisterUserUseCase {
    void registerUser(User user);
}
