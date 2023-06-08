package com.iobuilders.mylittebank.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TransactionMapperTest {

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Test
    void transactionToTransactionEntity_ok() {
        Transaction transaction = generateDeposit(generateWallet(generateUser()),BigDecimal.TEN);
        TransactionEntity transactionEntity  = generateTransactionEntity(transaction);

        when(modelMapper.map(transaction, TransactionEntity.class)).thenReturn(transactionEntity);

        TransactionEntity transactionEntityResult = transactionMapper.transactionToTransactionEntity(transaction);

        assertSame(transactionEntity, transactionEntityResult);

        assertEquals(transactionEntity.getTransactionId(),transactionEntityResult.getTransactionId());
        assertEquals(transactionEntity.getTransactionType(),transactionEntityResult.getTransactionType());
        assertEquals(transactionEntity.getAmount(),transactionEntityResult.getAmount());
        assertEquals(transactionEntity.getTransactionDateTime(),transactionEntityResult.getTransactionDateTime());
    }

    @Test
    void transactionToTransactionEntity_verify_mapper_calls() {
        Transaction transaction = generateDeposit(generateWallet(generateUser()),BigDecimal.TEN);
        TransactionEntity transactionEntity  = generateTransactionEntity(transaction);

        when(modelMapper.map(transaction, TransactionEntity.class)).thenReturn(transactionEntity);

        transactionMapper.transactionToTransactionEntity(transaction);

        verify(modelMapper).map(transaction, TransactionEntity.class);
    }

    @Test
    void transactionEntityToTransaction_ok() {
        Transaction transaction = generateDeposit(generateWallet(generateUser()),BigDecimal.TEN);
        TransactionEntity transactionEntity  = generateTransactionEntity(transaction);

        when(modelMapper.map(transactionEntity, Transaction.class)).thenReturn(transaction);

        Transaction transactionResult = transactionMapper.transactionEntityToTransaction(transactionEntity);

        assertSame(transaction, transactionResult);

        assertEquals(transaction.getTransactionId(),transactionResult.getTransactionId());
        assertEquals(transaction.getTransactionType(),transactionResult.getTransactionType());
        assertEquals(transaction.getAmount(),transactionResult.getAmount());
        assertEquals(transaction.getTransactionDateTime(),transactionResult.getTransactionDateTime());
    }

    @Test
    void transactionEntityToTransaction_verify_mapper_calls() {
        Transaction transaction = generateDeposit(generateWallet(generateUser()),BigDecimal.TEN);
        TransactionEntity transactionEntity  = generateTransactionEntity(transaction);

        when(modelMapper.map(transactionEntity, Transaction.class)).thenReturn(transaction);

        transactionMapper.transactionEntityToTransaction(transactionEntity);

        verify(modelMapper).map(transactionEntity, Transaction.class);
    }

}

