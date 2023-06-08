package com.iobuilders.mylittebank.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WalletMapper.class})
@ExtendWith(SpringExtension.class)
class WalletMapperTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private WalletMapper walletMapper;

    /**
     * Method under test: {@link WalletMapper#toWallet(WalletEntity)}
     */
    @Test
    void testToWallet() {
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
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Wallet>>any())).thenReturn(wallet);

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
        Wallet actualToWalletResult = walletMapper.toWallet(walletEntity);
        assertSame(wallet, actualToWalletResult);
        assertEquals("1", actualToWalletResult.getBalance().toString());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Wallet>>any());
    }

    /**
     * Method under test: {@link WalletMapper#toWalletEntity(Wallet)}
     */
    @Test
    void testToWalletEntity() {
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
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<WalletEntity>>any())).thenReturn(walletEntity);

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
        WalletEntity actualToWalletEntityResult = walletMapper.toWalletEntity(wallet);
        assertSame(walletEntity, actualToWalletEntityResult);
        assertEquals("1", actualToWalletEntityResult.getBalance().toString());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<WalletEntity>>any());
    }
}

