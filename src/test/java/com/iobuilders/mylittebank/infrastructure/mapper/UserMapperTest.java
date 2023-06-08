package com.iobuilders.mylittebank.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;

@ContextConfiguration(classes = {UserMapper.class})
@ExtendWith(SpringExtension.class)
class UserMapperTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void userEntityToUser_ok() {
        User user = generateUser();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<User>>any())).thenReturn(user);

        UserEntity userEntity = generateUserEntity(user);

        User userResult = userMapper.toUser(userEntity);

        assertSame(user, userResult);
        assertEquals(user.getName(), userResult.getName());
        assertEquals(user.getUserId(), userResult.getUserId());
        assertEquals(user.getEmail(), userResult.getEmail());
    }
    @Test
    void userEntityToUser_verify_mapper_calls() {
        User user = generateUser();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<User>>any())).thenReturn(user);

        UserEntity userEntity = generateUserEntity(user);

        userMapper.toUser(userEntity);

        verify(modelMapper).map(userEntity, User.class);
    }


    /**
     * Method under test: {@link UserMapper#toUserEntity(User)}
     */
    @Test
    void userToUserEntity_ok() {
        User user = generateUser();
        UserEntity userEntity = generateUserEntity(user);
        when(modelMapper.map(user, UserEntity.class)).thenReturn(userEntity);

        UserEntity userEntityResult = userMapper.toUserEntity(user);
        assertSame(userEntity, userEntityResult);

        assertEquals(user.getName(), userEntityResult.getName());
        assertEquals(user.getUserId(), userEntityResult.getUserId());
        assertEquals(user.getEmail(), userEntityResult.getEmail());

    }

    @Test
    void userToUserEntity_verify_mapper_calls() {
        User user = generateUser();
        UserEntity userEntity = generateUserEntity(user);
        when(modelMapper.map(user, UserEntity.class)).thenReturn(userEntity);

        assertSame(userEntity, userMapper.toUserEntity(user));
        verify(modelMapper).map(user, UserEntity.class);
    }

}

