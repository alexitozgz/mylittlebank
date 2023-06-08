package com.iobuilders.mylittebank.application.dto.mapper;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateDeposit;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.application.dto.request.RegisterUserRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;

import java.math.BigDecimal;
import java.util.HashSet;

import com.iobuilders.mylittebank.domain.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
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

    private RegisterUserRequest registerUserRequest;
    @BeforeEach
    void init() {
        User user = generateUser();

        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail(user.getEmail());
        registerUserRequest.setName(user.getName());
        registerUserRequest.setPhoneNumber(user.getPhoneNumber());

        when(modelMapper.map(registerUserRequest, User.class)).thenReturn(user);
        when(modelMapper.map(null,User.class)).thenThrow(NullPointerException.class);

    }

    @Test
    void fromRegisterUserRequestToUser_result_ok() {
        User userResult = registerUserRequestMapper.toUser(registerUserRequest);
        assertEquals(registerUserRequest.getEmail(), userResult.getEmail());
        assertEquals(registerUserRequest.getPhoneNumber(), userResult.getPhoneNumber());
        assertEquals(registerUserRequest.getName(), userResult.getName());
    }

    @Test
    void fromRegisterUserRequestToUser_verify_map_call() {
        registerUserRequestMapper.toUser(registerUserRequest);
        verify(modelMapper).map(registerUserRequest, User.class);
    }

    @Test
    void fromRegisterUserRequestToUser_result_ko() {
        assertThrows(NullPointerException.class, () -> registerUserRequestMapper.toUser(null));
    }


}

