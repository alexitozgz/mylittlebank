package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;

public class RegisterUserService implements RegisterUserUseCase {

    private RegisterUserPort registerUserPort;

    public RegisterUserService(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    @Override
    public void registerUser(User user) {
        registerUserPort.registerUser(user);
    }
}
