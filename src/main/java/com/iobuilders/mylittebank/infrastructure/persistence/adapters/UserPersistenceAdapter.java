package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainUserPort;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.UserRepository;

public class UserPersistenceAdapter implements RegisterUserPort, ObtainUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Long registerUser(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        return userRepository.save(userEntity).getUserId();
    }

    @Override
    public void existsUser(Long userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

}
