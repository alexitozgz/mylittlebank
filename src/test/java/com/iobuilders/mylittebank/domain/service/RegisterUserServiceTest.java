package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
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

@ContextConfiguration(classes = {RegisterUserService.class})
@ExtendWith(SpringExtension.class)
class RegisterUserServiceTest {
    @MockBean
    private RegisterUserPort registerUserPort;

    @Autowired
    private RegisterUserService registerUserService;

    /**
     * Method under test: {@link RegisterUserService#RegisterUserService(RegisterUserPort)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RegisterUserService.registerUserPort

        new RegisterUserService(mock(RegisterUserPort.class));
    }

    /**
     * Method under test: {@link RegisterUserService#registerUser(User)}
     */
    @Test
    void testRegisterUser() {
        doNothing().when(registerUserPort).registerUser(Mockito.<User>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        registerUserService.registerUser(user);
        verify(registerUserPort).registerUser(Mockito.<User>any());
        assertEquals("jane.doe@example.org", user.getEmail());
        assertTrue(user.getWallet().isEmpty());
        assertEquals(1L, user.getUserId());
        assertEquals("6625550144", user.getPhoneNumber());
        assertEquals("Name", user.getName());
    }
}

