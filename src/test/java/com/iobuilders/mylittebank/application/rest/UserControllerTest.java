package com.iobuilders.mylittebank.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.mylittebank.application.dto.mapper.RegisterUserRequestMapper;
import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.application.dto.request.RegisterUserRequest;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @MockBean
    private RegisterUserUseCase registerUserUseCase;

    @MockBean
    private RegisterUserRequestMapper registerUserRequestMapper;


    @Autowired
    private UserController userController;

    @Test
    void registerUser_ok() throws Exception {
        User user = generateUser();

        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("User created")));
    }

    @Test
    void registerUser_methodNotAllowed() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(userController).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void registerUser_walletNotFound() throws Exception {
        doThrow(NullPointerException.class).when(registerUserUseCase).registerUser(Mockito.any());

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();

        when(registerUserRequestMapper.toUser(registerUserRequest)).thenReturn(new User());

        String content = (new ObjectMapper()).writeValueAsString(registerUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        try {
            MockMvcBuilders.standaloneSetup(userController)
                    .build()
                    .perform(requestBuilder);
        } catch (Exception e){
            assertEquals(NullPointerException.class, e.getClass());
        }
    }



}

