package com.iobuilders.mylittebank.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserMapper.class})
@ExtendWith(SpringExtension.class)
class UserMapperTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * Method under test: {@link UserMapper#toUser(UserEntity)}
     */
    @Test
    void testToUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<User>>any())).thenReturn(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());
        assertSame(user, userMapper.toUser(userEntity));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<User>>any());
    }

    /**
     * Method under test: {@link UserMapper#toUserEntity(User)}
     */
    @Test
    void testToUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserEntity>>any())).thenReturn(userEntity);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        assertSame(userEntity, userMapper.toUserEntity(user));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserEntity>>any());
    }
}

