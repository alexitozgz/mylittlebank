package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.request.RegisterUserRequest;
import com.iobuilders.mylittebank.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterUserRequestMapper {

    @Autowired
    private ModelMapper modelMapper;

    public User toUser (RegisterUserRequest registerUserRequest){
        return modelMapper.map(registerUserRequest, User.class);
    }
}
