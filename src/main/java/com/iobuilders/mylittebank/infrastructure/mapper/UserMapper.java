package com.iobuilders.mylittebank.infrastructure.mapper;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public User toUser (UserEntity userEntity){
        return modelMapper.map(userEntity, User.class);
    }

    public UserEntity toUserEntity (User user){
        return modelMapper.map(user, UserEntity.class);
    }
}
