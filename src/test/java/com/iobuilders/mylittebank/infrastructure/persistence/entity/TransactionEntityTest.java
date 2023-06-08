package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class TransactionEntityTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link TransactionEntity}
     *   <li>{@link TransactionEntity#setAmount(BigDecimal)}
     *   <li>{@link TransactionEntity#setDestinationWallet(WalletEntity)}
     *   <li>{@link TransactionEntity#setOriginWallet(WalletEntity)}
     *   <li>{@link TransactionEntity#setTransactionDateTime(LocalDateTime)}
     *   <li>{@link TransactionEntity#setTransactionId(long)}
     *   <li>{@link TransactionEntity#setTransactionType(String)}
     *   <li>{@link TransactionEntity#getAmount()}
     *   <li>{@link TransactionEntity#getDestinationWallet()}
     *   <li>{@link TransactionEntity#getOriginWallet()}
     *   <li>{@link TransactionEntity#getTransactionDateTime()}
     *   <li>{@link TransactionEntity#getTransactionId()}
     *   <li>{@link TransactionEntity#getTransactionType()}
     * </ul>
     */
    @Test
    void testConstructor() {
        TransactionEntity actualTransactionEntity = new TransactionEntity();
        actualTransactionEntity.setAmount(BigDecimal.valueOf(1L));
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());
        WalletEntity destinationWallet = new WalletEntity();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setUser(userEntity);
        destinationWallet.setWalletId(1L);
        actualTransactionEntity.setDestinationWallet(destinationWallet);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setName("Name");
        userEntity2.setPhoneNumber("6625550144");
        userEntity2.setUserId(1L);
        userEntity2.setWallet(new HashSet<>());
        WalletEntity originWallet = new WalletEntity();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setUser(userEntity2);
        originWallet.setWalletId(1L);
        actualTransactionEntity.setOriginWallet(originWallet);
        LocalDateTime transactionDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualTransactionEntity.setTransactionDateTime(transactionDateTime);
        actualTransactionEntity.setTransactionId(1L);
        actualTransactionEntity.setTransactionType("Transaction Type");
        BigDecimal expectedAmount = balance.ONE;
        assertSame(expectedAmount, actualTransactionEntity.getAmount());
        assertSame(destinationWallet, actualTransactionEntity.getDestinationWallet());
        assertSame(originWallet, actualTransactionEntity.getOriginWallet());
        assertSame(transactionDateTime, actualTransactionEntity.getTransactionDateTime());
        assertEquals(1L, actualTransactionEntity.getTransactionId());
        assertEquals("Transaction Type", actualTransactionEntity.getTransactionType());
    }
}

