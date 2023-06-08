package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegisterUserService implements RegisterUserUseCase {

    private final RegisterUserPort registerUserPort;

    public RegisterUserService(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    @Override
    public Long registerUser(User user) {
        log.debug("Starting domain service registerUser with user {}", user);
        return registerUserPort.registerUser(user);
    }
}
