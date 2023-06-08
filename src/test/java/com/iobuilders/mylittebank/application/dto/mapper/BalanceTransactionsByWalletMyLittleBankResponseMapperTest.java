package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.response.BalanceTransactionsByWalletResponse;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateTransactionsList;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.mockWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {BalanceTransactionsByWalletResponseMapper.class})
@ExtendWith(SpringExtension.class)
class BalanceTransactionsByWalletMyLittleBankResponseMapperTest {

    @Autowired
    private BalanceTransactionsByWalletResponseMapper balanceTransactionsByWalletResponseMapper;



    @Test
    void fromWalletToBalanceTransactionsByWallet_result_ok() {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        BigDecimal balance = BigDecimal.valueOf(1);
        wallet.setBalance(balance);

        ArrayList<Transaction> transactionList = generateTransactionsList(wallet);
        wallet.setTransactionList(transactionList);

        BalanceTransactionsByWalletResponse result = balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(wallet);

        assertSame(balance, result.getBalance());
        assertEquals(1L, result.getWalletId());
        assertEquals(transactionList.get(0).getTransactionId(), result.getTransactionResponseList().get(0).getTransactionId());
        assertEquals(transactionList.get(0).getDestinationWallet().getWalletId(), result.getTransactionResponseList().get(0).getDestinationWallet());
        assertEquals(transactionList.get(0).getAmount(), result.getTransactionResponseList().get(0).getAmount());
        assertEquals(transactionList.get(0).getTransactionDateTime(), result.getTransactionResponseList().get(0).getTransactionDateTime());
    }



    @Test
    void fromWalletToBalanceTransactionsByWallet_verify_wallet_setter_calls() {
        User user = generateUser();
        Wallet wallet = mockWallet(BigDecimal.valueOf(1), new ArrayList<>());

        wallet.setBalance(BigDecimal.valueOf(1L));
        ArrayList<Transaction> transactionList = new ArrayList<>();
        wallet.setTransactionList(transactionList);
        wallet.setUser(user);
        wallet.setWalletId(1L);

        balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(wallet);

        verify(wallet).getBalance();
        verify(wallet).getTransactionList();
        verify(wallet).getWalletId();
        verify(wallet).setBalance(BigDecimal.valueOf(1L));
        verify(wallet).setTransactionList(transactionList);
        verify(wallet).setUser(user);
        verify(wallet).setWalletId(1L);
    }
    @Test
    void fromWalletToBalanceTransactionsByWallet_no_transactions() {
        Wallet wallet = mockWallet(BigDecimal.valueOf(1), new ArrayList<>());

        balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(wallet);

        verify(wallet).getBalance();
        verify(wallet).getTransactionList();
        verify(wallet).getWalletId();
        verify(wallet, times(0)).setTransactionList(Mockito.anyList());
    }

    @Test
    void fromWalletToBalanceTransactionsByWalletResponseMapper_result_ko() {
        assertThrows(NullPointerException.class, () -> balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(null));
    }

}

