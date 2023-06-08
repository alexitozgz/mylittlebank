package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
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
class WalletPersistenceAdapterTest {
    @MockBean
    private WalletMapper walletMapper;

    @Autowired
    private WalletPersistenceAdapter walletPersistenceAdapter;

    @MockBean
    private WalletRepository walletRepository;

    /**
     * Method under test: {@link WalletPersistenceAdapter#WalletPersistenceAdapter(WalletRepository, WalletMapper)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     WalletPersistenceAdapter.walletMapper
        //     WalletPersistenceAdapter.walletRepository

        WalletRepository walletRepository = mock(WalletRepository.class);
        new WalletPersistenceAdapter(walletRepository, new WalletMapper());

    }

    /**
     * Method under test: {@link WalletPersistenceAdapter#createWallet(Long)}
     */
    @Test
    void testCreateWallet() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(BigDecimal.valueOf(1L));
        walletEntity.setUser(userEntity);
        walletEntity.setWalletId(1L);
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenReturn(walletEntity);
        walletPersistenceAdapter.createWallet(1L);
        verify(walletRepository).save(Mockito.<WalletEntity>any());
    }

    /**
     * Method under test: {@link WalletPersistenceAdapter#obtainWalletPort(Long)}
     */
    @Test
    void testObtainWalletPort() throws UserNotFoundException {
        assertThrows(UserNotFoundException.class, () -> walletPersistenceAdapter.obtainWalletPort(1L));
        assertThrows(UserNotFoundException.class, () -> walletPersistenceAdapter.obtainWalletPort(2L));
    }

    /**
     * Method under test: {@link WalletPersistenceAdapter#updateWallet(Wallet)}
     */
    @Test
    void testUpdateWallet() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(BigDecimal.valueOf(1L));
        walletEntity.setUser(userEntity);
        walletEntity.setWalletId(1L);
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenReturn(walletEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setName("Name");
        userEntity2.setPhoneNumber("6625550144");
        userEntity2.setUserId(1L);
        userEntity2.setWallet(new HashSet<>());

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.setBalance(BigDecimal.valueOf(1L));
        walletEntity2.setUser(userEntity2);
        walletEntity2.setWalletId(1L);
        when(walletMapper.toWalletEntity(Mockito.<Wallet>any())).thenReturn(walletEntity2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user);
        wallet.setWalletId(1L);
        walletPersistenceAdapter.updateWallet(wallet);
        verify(walletRepository).save(Mockito.<WalletEntity>any());
        verify(walletMapper).toWalletEntity(Mockito.<Wallet>any());
    }
}

