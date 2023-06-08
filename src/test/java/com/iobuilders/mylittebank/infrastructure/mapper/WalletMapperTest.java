package com.iobuilders.mylittebank.infrastructure.mapper;

import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.util.MyLittleBankTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {WalletMapper.class})
@ExtendWith(SpringExtension.class)
class WalletMapperTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Test
    void toWalletEntity_ok() {
        Wallet wallet = generateWallet(generateUser());
        WalletEntity walletEntity  = MyLittleBankTestUtils.generateWalletEntity(wallet);

        when(modelMapper.map(wallet, WalletEntity.class)).thenReturn(walletEntity);

        WalletEntity walletEntityResult = walletMapper.toWalletEntity(wallet);

        assertSame(walletEntity, walletEntityResult);

        assertEquals(walletEntity.getWalletId(),walletEntityResult.getWalletId());
        assertEquals(walletEntity.getBalance(),walletEntityResult.getBalance());
        assertEquals(walletEntity.getUser(),walletEntityResult.getUser());

    }

    @Test
    void toWalletEntity_verify_mapper_calls() {
        Wallet wallet = generateWallet(generateUser());
        WalletEntity walletEntity  = MyLittleBankTestUtils.generateWalletEntity(wallet);

        when(modelMapper.map(wallet, WalletEntity.class)).thenReturn(walletEntity);

        walletMapper.toWalletEntity(wallet);

        verify(modelMapper).map(wallet, WalletEntity.class);
    }

    @Test
    void toWallet_ok() {
        Wallet wallet = generateWallet(generateUser());
        WalletEntity walletEntity  = MyLittleBankTestUtils.generateWalletEntity(wallet);

        when(modelMapper.map(walletEntity, Wallet.class)).thenReturn(wallet);

        Wallet walletResult = walletMapper.toWallet(walletEntity);

        assertSame(wallet, walletResult);

        assertEquals(wallet.getWalletId(),walletResult.getWalletId());
        assertEquals(wallet.getBalance(),walletResult.getBalance());
        assertEquals(wallet.getUser(),walletResult.getUser());
    }

    @Test
    void toWallet_verify_mapper_calls() {
        Wallet wallet = generateWallet(generateUser());
        WalletEntity walletEntity  = MyLittleBankTestUtils.generateWalletEntity(wallet);

        when(modelMapper.map(walletEntity, Wallet.class)).thenReturn(wallet);

        walletMapper.toWallet(walletEntity);

        verify(modelMapper).map(walletEntity, Wallet.class);
    }

}

