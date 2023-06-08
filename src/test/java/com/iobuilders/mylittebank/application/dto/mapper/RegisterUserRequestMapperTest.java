package com.iobuilders.mylittebank.application.dto.mapper;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.application.dto.request.RegisterUserRequest;
import com.iobuilders.mylittebank.domain.model.User;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RegisterUserRequestMapper.class})
@ExtendWith(SpringExtension.class)
class RegisterUserRequestMapperTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private RegisterUserRequestMapper registerUserRequestMapper;

    /**
     * Method under test: {@link RegisterUserRequestMapper#toUser(RegisterUserRequest)}
     */
    @Test
    void testToUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<User>>any())).thenReturn(user);

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("jane.doe@example.org");
        registerUserRequest.setName("Name");
        registerUserRequest.setPhoneNumber("6625550144");
        assertSame(user, registerUserRequestMapper.toUser(registerUserRequest));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<User>>any());
    }
}

