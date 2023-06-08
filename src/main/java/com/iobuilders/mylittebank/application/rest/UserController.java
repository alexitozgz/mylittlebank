package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.mapper.RegisterUserRequestMapper;
import com.iobuilders.mylittebank.application.dto.request.RegisterUserRequest;
import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;
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
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final RegisterUserRequestMapper registerUserRequestMapper;


    public UserController(RegisterUserUseCase registerUserUseCase, RegisterUserRequestMapper registerUserRequestMapper) {
        this.registerUserUseCase = registerUserUseCase;
        this.registerUserRequestMapper = registerUserRequestMapper;
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@Valid @NotNull @RequestBody RegisterUserRequest userRequest) {
        User user = registerUserRequestMapper.toUser(userRequest);
        registerUserUseCase.registerUser(user);
        return ResponseEntity.ok(new MyLittleBankResponse(HttpStatus.CREATED, "User created"));
    }
}
