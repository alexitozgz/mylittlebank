package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.mapper.RegisterUserRequestMapper;
import com.iobuilders.mylittebank.application.dto.request.RegisterUserRequest;
import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final RegisterUserRequestMapper registerUserRequestMapper;


    public UserController(RegisterUserUseCase registerUserUseCase, RegisterUserRequestMapper registerUserRequestMapper) {
        this.registerUserUseCase = registerUserUseCase;
        this.registerUserRequestMapper = registerUserRequestMapper;
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@Valid @NotNull @RequestBody RegisterUserRequest userRequest) {
        log.info("Starting registerUser {}", userRequest);
        User user = registerUserRequestMapper.toUser(userRequest);
        Long userId = registerUserUseCase.registerUser(user);
        log.info("Finishing registerUser successfully");
        return new ResponseEntity<>(new MyLittleBankResponse(HttpStatus.CREATED, "User created with id "+userId), HttpStatus.CREATED);
    }
}
