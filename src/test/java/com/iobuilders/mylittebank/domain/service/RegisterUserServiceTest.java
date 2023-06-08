package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;
@ContextConfiguration(classes = {RegisterUserService.class})
@ExtendWith(SpringExtension.class)
class RegisterUserServiceTest {
    @MockBean
    private RegisterUserPort registerUserPort;

    @Autowired
    private RegisterUserService registerUserService;
    @Test
    void registerUser_ok() {
        User user = generateUser();
        Long userId = 20L;
        doReturn(userId).when(registerUserPort).registerUser(user);

        assertEquals(userId, registerUserService.registerUser(user));
    }

    @Test
    void registerUser_verify_port_calls() {
        User user = generateUser();
        Long userId = 20L;
        doReturn(userId).when(registerUserPort).registerUser(user);

        registerUserService.registerUser(user);

        verify(registerUserPort).registerUser(user);
    }

}

