package com.iobuilders.mylittebank.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.mylittebank.application.dto.mapper.RegisterUserRequestMapper;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @MockBean
    private RegisterUserUseCase registerUserUseCase;

    @MockBean
    private RegisterUserRequestMapper registerUserRequestMapper;


    @Autowired
    private UserController userController;



    /**
     * Method under test: {@link UserController#registerUser(User)}
     */
    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

