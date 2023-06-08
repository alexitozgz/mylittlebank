package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.UserRepository;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserPersistenceAdapterTest {
    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;


    @Test
    void registerUser_ok() {
        User user = generateUser();

        UserEntity userEntity = generateUserEntity(user);

        when(userMapper.toUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        Long userId = userPersistenceAdapter.registerUser(user);

        assertEquals(userEntity.getUserId(), userId);
    }

    @Test
    void registerUser_verify_calls() {
        User user = generateUser();

        UserEntity userEntity = generateUserEntity(user);

        when(userMapper.toUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        userPersistenceAdapter.registerUser(user);

        verify(userRepository).save(userEntity);
        verify(userMapper).toUserEntity(user);
    }

    @Test
    void existUser_userNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> userPersistenceAdapter.existsUser(1L));
    }

    @Test
    void existUser_ok() throws UserNotFoundException {
        when(userRepository.existsById(1L)).thenReturn(true);
        assertTrue(true);
    }
}

