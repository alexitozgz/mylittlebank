package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.mapper.TransactionMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
class TransactionPersistenceAdapterTest {
    @MockBean
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionPersistenceAdapter transactionPersistenceAdapter;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private WalletMapper walletMapper;


    @Test
    void createDeposit_ok() {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        Transaction transaction = generateDeposit(wallet, BigDecimal.TEN);

        TransactionEntity transactionEntity = generateTransactionEntity(transaction);

        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionMapper.transactionToTransactionEntity(transaction)).thenReturn(transactionEntity);

        Long transactionEntityResultId = transactionPersistenceAdapter.createDeposit(transaction);
        assertEquals(transactionEntity.getTransactionId(),transactionEntityResultId);
    }


    @Test
    void createDeposit_verify_repo_mapper_calls() {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        Transaction transaction = generateDeposit(wallet, BigDecimal.TEN);

        TransactionEntity transactionEntity = generateTransactionEntity(transaction);

        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionMapper.transactionToTransactionEntity(transaction)).thenReturn(transactionEntity);

        transactionPersistenceAdapter.createDeposit(transaction);

        verify(transactionRepository).save(transactionEntity);
        verify(transactionMapper).transactionToTransactionEntity(transaction);
    }

    @Test
    void createTransfer_ok() {
        User user = generateUser();

        Wallet destinationWallet = generateWallet(user);

        Wallet originWallet = generateWallet(user);

        Transaction transaction= generateTransfer(destinationWallet, originWallet, BigDecimal.TEN);

        TransactionEntity transactionEntity = generateTransactionEntity(transaction);

        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionMapper.transactionToTransactionEntity(transaction)).thenReturn(transactionEntity);

        transactionPersistenceAdapter.createDeposit(transaction);

        Long transactionEntityResultId = transactionPersistenceAdapter.createDeposit(transaction);
        assertEquals(transactionEntity.getTransactionId(),transactionEntityResultId);
    }

    @Test
    void createTransfer_verify_repo_mapper_calls() {
        User user = generateUser();

        Wallet destinationWallet = generateWallet(user);

        Wallet originWallet = generateWallet(user);

        Transaction transaction= generateTransfer(destinationWallet, originWallet, BigDecimal.TEN);

        TransactionEntity transactionEntity = generateTransactionEntity(transaction);

        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionMapper.transactionToTransactionEntity(transaction)).thenReturn(transactionEntity);

        transactionPersistenceAdapter.createDeposit(transaction);

        verify(transactionRepository).save(transactionEntity);
        verify(transactionMapper).transactionToTransactionEntity(transaction);
    }

    @Test
    void obtainTransactionsByWallet_ok() {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        Transaction transaction = generateDeposit(wallet, BigDecimal.TEN);

        TransactionEntity transactionEntity = generateTransactionEntity(transaction);

        List<TransactionEntity> transactionEntityList = new ArrayList<>();
        transactionEntityList.add(transactionEntity);

        when(transactionRepository.getTransactionEntitiesByOriginWalletOrDestinationWallet(wallet.getWalletId())).thenReturn(transactionEntityList);
        when(transactionMapper.transactionEntityToTransaction(transactionEntity)).thenReturn(transaction);

        List<Transaction> transactionList = transactionPersistenceAdapter.obtainTransactionsByWallet(wallet);

        assertEquals(transaction.getTransactionId(),transactionList.get(0).getTransactionId());
        assertEquals(transaction.getAmount(),transactionList.get(0).getAmount());
    }

    @Test
    void obtainTransactionsByWallet_empty() {
        User user = generateUser();

        Wallet wallet = generateWallet(user);

        assertTrue(transactionPersistenceAdapter.obtainTransactionsByWallet(wallet).isEmpty());
    }


}

