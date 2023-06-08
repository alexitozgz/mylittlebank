package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;
@ContextConfiguration(classes = {MakeDepositService.class})
@ExtendWith(SpringExtension.class)
class MakeDepositServiceTest {
    @MockBean
    private MakeDepositPort makeDepositPort;

    @Autowired
    private MakeDepositService makeDepositService;

    @MockBean
    private ObtainWalletPort obtainWalletPort;

    @MockBean
    private UpdateWalletPort updateWalletPort;

    @Test
    void makeDeposit_ok() throws WalletNotFoundException {
        User user = generateUser();
        Wallet wallet = generateWallet(user);

        Transaction transaction = generateDeposit(wallet, BigDecimal.TEN);

        when(obtainWalletPort.obtainWallet(wallet.getWalletId())).thenReturn(wallet);
        when(makeDepositPort.createDeposit(transaction)).thenReturn(33L);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());

        assertEquals(BigDecimal.ZERO, wallet.getBalance());
        assertEquals(33L, makeDepositService.makeDeposit(transaction));
        assertEquals(BigDecimal.TEN, wallet.getBalance());
    }

    @Test
    void makeDeposit_verify_port_calls() throws WalletNotFoundException {
        User user = generateUser();
        Wallet wallet = generateWallet(user);

        Transaction transaction = generateDeposit(wallet, BigDecimal.TEN);

        when(obtainWalletPort.obtainWallet(wallet.getWalletId())).thenReturn(wallet);
        when(makeDepositPort.createDeposit(transaction)).thenReturn(33L);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());

        makeDepositService.makeDeposit(transaction);

        verify(makeDepositPort).createDeposit(transaction);
        verify(obtainWalletPort).obtainWallet(transaction.getDestinationWallet().getWalletId());
        verify(updateWalletPort).updateWallet(transaction.getDestinationWallet());
    }

    @Test
    void makeDeposit_walletNotFoundException() throws  WalletNotFoundException {
        Transaction transaction = generateDeposit(generateWallet(generateUser()),BigDecimal.TEN);
        doThrow(new WalletNotFoundException(transaction.getDestinationWallet().getWalletId())).when(obtainWalletPort).obtainWallet(transaction.getDestinationWallet().getWalletId());
        assertThrows(WalletNotFoundException.class, () -> makeDepositService.makeDeposit(transaction));
    }


}

