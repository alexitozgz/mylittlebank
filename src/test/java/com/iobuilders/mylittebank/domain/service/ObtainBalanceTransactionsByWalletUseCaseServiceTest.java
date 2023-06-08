package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateTransactionsList;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ObtainBalanceTransactionsByWalletUseCaseService.class})
@ExtendWith(SpringExtension.class)
class ObtainBalanceTransactionsByWalletUseCaseServiceTest {
    public static final long WALLET_ID = 2L;
    @Autowired
    private ObtainBalanceTransactionsByWalletUseCaseService obtainBalanceTransactionsByWalletUseCaseService;

    @MockBean
    private ObtainTransactionsByWalletPort obtainTransactionsByWalletPort;

    @MockBean
    private ObtainWalletPort obtainWalletPort;


    @Test
    void obtainBalanceTransactionsByWallet_ok() throws WalletNotFoundException {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        List<Transaction> transactionList = generateTransactionsList(wallet);

        when(obtainTransactionsByWalletPort.obtainTransactionsByWallet(wallet)).thenReturn(transactionList);
        when(obtainWalletPort.obtainWallet(wallet.getWalletId())).thenReturn(wallet);

        Wallet walletResult = obtainBalanceTransactionsByWalletUseCaseService.obtainBalanceTransactionsByWallet(wallet.getWalletId());

        assertSame(wallet, walletResult);
        assertEquals(transactionList, walletResult.getTransactionList());
        assertEquals(wallet.getBalance(), walletResult.getBalance());
    }

    @Test
    void obtainBalanceTransactionsByWallet_verify_port_calls() throws WalletNotFoundException {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        List<Transaction> transactionList = generateTransactionsList(wallet);

        when(obtainTransactionsByWalletPort.obtainTransactionsByWallet(wallet)).thenReturn(transactionList);
        when(obtainWalletPort.obtainWallet(wallet.getWalletId())).thenReturn(wallet);

        obtainBalanceTransactionsByWalletUseCaseService.obtainBalanceTransactionsByWallet(wallet.getWalletId());

        verify(obtainTransactionsByWalletPort).obtainTransactionsByWallet(wallet);
        verify(obtainWalletPort).obtainWallet(wallet.getWalletId());
    }

    @Test
    void obtainBalanceTransactionsByWallet_WalletNotFoundException() throws WalletNotFoundException {
        when(obtainTransactionsByWalletPort.obtainTransactionsByWallet(Mockito.any()))
                .thenReturn(new ArrayList<>());
        when(obtainWalletPort.obtainWallet(WALLET_ID))
                .thenThrow(new WalletNotFoundException(WALLET_ID));
        assertThrows(WalletNotFoundException.class,
                () -> obtainBalanceTransactionsByWalletUseCaseService.obtainBalanceTransactionsByWallet(WALLET_ID));
        verify(obtainWalletPort).obtainWallet(Mockito.<Long>any());
    }
}

