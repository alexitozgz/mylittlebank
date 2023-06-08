package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

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
class WalletPersistenceAdapterTest {
    @MockBean
    private WalletMapper walletMapper;

    @Autowired
    private WalletPersistenceAdapter walletPersistenceAdapter;

    @MockBean
    private WalletRepository walletRepository;


    @Test
    void createWallet_ok() {
        User user = new User();
        Wallet wallet = generateWallet(user);
        WalletEntity walletEntity = generateWalletEntity(wallet);

        when(walletRepository.save(Mockito.any())).thenReturn(walletEntity);

        assertEquals(walletEntity.getWalletId(), walletPersistenceAdapter.createWallet(user.getUserId()));
    }

    @Test
    void createWallet_verify_repo_calls() {
        User user = new User();
        Wallet wallet = generateWallet(user);
        WalletEntity walletEntity = generateWalletEntity(wallet);

        when(walletRepository.save(Mockito.any())).thenReturn(walletEntity);

        walletPersistenceAdapter.createWallet(user.getUserId());

        verify(walletRepository).save(Mockito.any());
    }

    @Test
    void obtainWallet_ok() throws WalletNotFoundException {
        User user = new User();
        Wallet wallet = generateWallet(user);
        WalletEntity walletEntity = generateWalletEntity(wallet);

        when(walletRepository.findById(wallet.getWalletId())).thenReturn(Optional.of(walletEntity));
        when(walletMapper.toWallet(walletEntity)).thenReturn(wallet);

        assertEquals(wallet.getWalletId(),walletPersistenceAdapter.obtainWallet(wallet.getWalletId()).getWalletId());
    }

    @Test
    void obtainWallet_verify_repo_calls() throws WalletNotFoundException {
        User user = new User();
        Wallet wallet = generateWallet(user);
        WalletEntity walletEntity = generateWalletEntity(wallet);

        when(walletRepository.findById(wallet.getWalletId())).thenReturn(Optional.of(walletEntity));
        when(walletMapper.toWallet(walletEntity)).thenReturn(wallet);

        walletPersistenceAdapter.obtainWallet(wallet.getWalletId());

        verify(walletRepository).findById(wallet.getWalletId());
        verify(walletMapper).toWallet(walletEntity);
    }

    @Test
    void obtainWallet_walletNotFoundException(){
        assertThrows(WalletNotFoundException.class, () -> walletPersistenceAdapter.obtainWallet(1L));
    }

    @Test
    void updateWallet_verify_repo_mapper_calls() {
        User user = new User();
        Wallet wallet = generateWallet(user);
        WalletEntity walletEntity = generateWalletEntity(wallet);

        when(walletMapper.toWalletEntity(wallet)).thenReturn(walletEntity);
        when(walletRepository.save(walletEntity)).thenReturn(walletEntity);

        walletPersistenceAdapter.updateWallet(wallet);

        verify(walletRepository).save(walletEntity);
        verify(walletMapper).toWalletEntity(wallet);
    }

}

