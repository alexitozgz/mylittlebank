package com.iobuilders.mylittebank.util;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.model.enumerations.TransactionType;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import org.jetbrains.annotations.NotNull;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyLittleBankTestUtils {
    
    public static Wallet generateWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setWalletId(1L);
        wallet.setBalance(BigDecimal.ZERO);
        return wallet;
    }

    public static User generateUser() {
        User user = new User();
        user.setEmail("mailfake@mimail.es");
        user.setName("UnoMas");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        return user;
    }

    public static ArrayList<Transaction> generateTransactionsList(Wallet wallet) {
        ArrayList<Transaction> transactionList = new ArrayList<>();

        Transaction transaction = generateDeposit(wallet, BigDecimal.TEN);

        transactionList.add(transaction);
        return transactionList;
    }

    @NotNull
    public static Transaction generateDeposit(Wallet wallet, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT.name());
        transaction.setAmount(amount);
        transaction.setDestinationWallet(wallet);
        transaction.setTransactionDateTime(LocalDate.of(1980, 1, 1).atStartOfDay());

        return transaction;
    }

    public static Transaction generateTransfer(Wallet destinationWallet, Wallet originWallet, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER.name());
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setAmount(amount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        return transaction;
    }

    public static Wallet mockWallet(BigDecimal balance, ArrayList<Transaction> transactionList) {
        Wallet wallet = mock(Wallet.class);
        when(wallet.getBalance()).thenReturn(balance);
        when(wallet.getTransactionList()).thenReturn(transactionList);
        when(wallet.getWalletId()).thenReturn(1L);
        doNothing().when(wallet).setBalance(Mockito.any());
        doNothing().when(wallet).setTransactionList(Mockito.any());
        doNothing().when(wallet).setUser(Mockito.any());
        doNothing().when(wallet).setWalletId(anyLong());
        return wallet;
    }

    public static UserEntity generateUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setUserId(user.getUserId());
        userEntity.setWallet(new HashSet<>());
        return userEntity;
    }

    public static TransactionEntity generateTransactionEntity(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionType(transaction.getTransactionType());
        transactionEntity.setTransactionId(transaction.getTransactionId());
        transactionEntity.setTransactionDateTime(transaction.getTransactionDateTime());
        transactionEntity.setAmount(transaction.getAmount());
        return transactionEntity;
    }

    public static WalletEntity generateWalletEntity(Wallet wallet) {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(wallet.getBalance());
        walletEntity.setWalletId(wallet.getWalletId());
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(wallet.getUser().getUserId());
        userEntity.setEmail(wallet.getUser().getEmail());
        userEntity.setPhoneNumber(wallet.getUser().getPhoneNumber());
        walletEntity.setUser(userEntity);
        return walletEntity;
    }
}
