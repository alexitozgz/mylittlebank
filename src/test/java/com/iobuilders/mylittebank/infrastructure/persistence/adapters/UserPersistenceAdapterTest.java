package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.UserRepository;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserPersistenceAdapterTest {
    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    /**
     * Method under test: {@link UserPersistenceAdapter#UserPersistenceAdapter(UserRepository, UserMapper)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     UserPersistenceAdapter.userMapper
        //     UserPersistenceAdapter.userRepository

        UserRepository userRepository = mock(UserRepository.class);
        new UserPersistenceAdapter(userRepository, new UserMapper());

    }

    /**
     * Method under test: {@link UserPersistenceAdapter#registerUser(User)}
     */
    @Test
    void testRegisterUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());
        when(userRepository.save(Mockito.<UserEntity>any())).thenReturn(userEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setName("Name");
        userEntity2.setPhoneNumber("6625550144");
        userEntity2.setUserId(1L);
        userEntity2.setWallet(new HashSet<>());
        when(userMapper.toUserEntity(Mockito.<User>any())).thenReturn(userEntity2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        userPersistenceAdapter.registerUser(user);
        verify(userRepository).save(Mockito.<UserEntity>any());
        verify(userMapper).toUserEntity(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserPersistenceAdapter#obtainUser(Long)}
     */
    @Test
    void testObtainUser() throws UserNotFoundException {
        assertThrows(UserNotFoundException.class, () -> userPersistenceAdapter.obtainUser(1L));
        assertThrows(UserNotFoundException.class, () -> userPersistenceAdapter.obtainUser(2L));
    }
}

