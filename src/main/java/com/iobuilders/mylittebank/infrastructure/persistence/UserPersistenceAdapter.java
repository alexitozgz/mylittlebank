package com.iobuilders.mylittebank.infrastructure.persistence;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.domain.repository.UserRepository;
import com.iobuilders.mylittebank.domain.service.UserService;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter implements RegisterUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void registerUser(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        userRepository.saveUser(userEntity);
    }

//    @Override
//    public User getUser(long id) {
//        return null;
//    }
}
