package com.iobuilders.mylittebank.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TransactionMapperTest {
    @Autowired
    private TransactionMapper transactionMapper;

    /**
     * Method under test: {@link TransactionMapper#transactionToTransactionEntity(Transaction)}
     */
    @Test
    void testTransactionToTransactionEntity() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user);
        destinationWallet.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        TransactionEntity actualTransactionToTransactionEntityResult = transactionMapper
                .transactionToTransactionEntity(transaction);
        BigDecimal expectedAmount = balance.ONE;
        BigDecimal amount = actualTransactionToTransactionEntityResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionToTransactionEntityResult.getTransactionType());
        assertEquals(1L, actualTransactionToTransactionEntityResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionToTransactionEntityResult.getTransactionDateTime().toLocalDate().toString());
        WalletEntity destinationWallet2 = actualTransactionToTransactionEntityResult.getDestinationWallet();
        assertSame(amount, destinationWallet2.getBalance());
        WalletEntity originWallet2 = actualTransactionToTransactionEntityResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertSame(amount, originWallet2.getBalance());
        assertEquals("1", amount.toString());
        assertEquals(1L, destinationWallet2.getWalletId());
        UserEntity user3 = destinationWallet2.getUser();
        assertTrue(user3.getWallet().isEmpty());
        assertEquals(1L, user3.getUserId());
        assertEquals("6625550144", user3.getPhoneNumber());
        assertEquals("Name", user3.getName());
        assertEquals("jane.doe@example.org", user3.getEmail());
        UserEntity user4 = originWallet2.getUser();
        assertEquals("Name", user4.getName());
        assertEquals(1L, user4.getUserId());
        assertTrue(user4.getWallet().isEmpty());
        assertEquals("jane.doe@example.org", user4.getEmail());
        assertEquals("6625550144", user4.getPhoneNumber());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionToTransactionEntity(Transaction)}
     */
    @Test
    void testTransactionToTransactionEntity2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user);
        destinationWallet.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(0L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        TransactionEntity actualTransactionToTransactionEntityResult = transactionMapper
                .transactionToTransactionEntity(transaction);
        BigDecimal expectedAmount = balance.ZERO;
        BigDecimal amount = actualTransactionToTransactionEntityResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionToTransactionEntityResult.getTransactionType());
        assertEquals(1L, actualTransactionToTransactionEntityResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionToTransactionEntityResult.getTransactionDateTime().toLocalDate().toString());
        BigDecimal expectedBalance = balance.ONE;
        WalletEntity destinationWallet2 = actualTransactionToTransactionEntityResult.getDestinationWallet();
        BigDecimal balance2 = destinationWallet2.getBalance();
        assertSame(expectedBalance, balance2);
        WalletEntity originWallet2 = actualTransactionToTransactionEntityResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertEquals("0", amount.toString());
        assertEquals(1L, destinationWallet2.getWalletId());
        assertSame(balance2, originWallet2.getBalance());
        UserEntity user3 = destinationWallet2.getUser();
        assertEquals(1L, user3.getUserId());
        assertEquals("6625550144", user3.getPhoneNumber());
        assertEquals("Name", user3.getName());
        assertEquals("jane.doe@example.org", user3.getEmail());
        UserEntity user4 = originWallet2.getUser();
        assertEquals("6625550144", user4.getPhoneNumber());
        assertEquals("Name", user4.getName());
        assertEquals("jane.doe@example.org", user4.getEmail());
        assertEquals(1L, user4.getUserId());
        assertTrue(user4.getWallet().isEmpty());
        assertTrue(user3.getWallet().isEmpty());
        assertEquals("1", balance2.toString());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionToTransactionEntity(Transaction)}
     */
    @Test
    void testTransactionToTransactionEntity3() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user);
        destinationWallet.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(null);
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        TransactionEntity actualTransactionToTransactionEntityResult = transactionMapper
                .transactionToTransactionEntity(transaction);
        assertNull(actualTransactionToTransactionEntityResult.getAmount());
        assertEquals("Transaction Type", actualTransactionToTransactionEntityResult.getTransactionType());
        assertEquals(1L, actualTransactionToTransactionEntityResult.getTransactionId());
        assertEquals("00:00",
                actualTransactionToTransactionEntityResult.getTransactionDateTime().toLocalTime().toString());
        WalletEntity destinationWallet2 = actualTransactionToTransactionEntityResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = destinationWallet2.getBalance();
        assertSame(expectedBalance, balance2);
        WalletEntity originWallet2 = actualTransactionToTransactionEntityResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertSame(balance2, originWallet2.getBalance());
        UserEntity user3 = originWallet2.getUser();
        assertTrue(user3.getWallet().isEmpty());
        assertEquals(1L, user3.getUserId());
        assertEquals("6625550144", user3.getPhoneNumber());
        assertEquals("Name", user3.getName());
        assertEquals("jane.doe@example.org", user3.getEmail());
        UserEntity user4 = destinationWallet2.getUser();
        assertEquals(1L, user4.getUserId());
        assertEquals("6625550144", user4.getPhoneNumber());
        assertEquals("Name", user4.getName());
        assertEquals("jane.doe@example.org", user4.getEmail());
        assertTrue(user4.getWallet().isEmpty());
        assertEquals("1", balance2.toString());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionToTransactionEntity(Transaction)}
     */
    @Test
    void testTransactionToTransactionEntity4() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(null);
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user);
        destinationWallet.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        TransactionEntity actualTransactionToTransactionEntityResult = transactionMapper
                .transactionToTransactionEntity(transaction);
        BigDecimal expectedAmount = balance.ONE;
        BigDecimal amount = actualTransactionToTransactionEntityResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionToTransactionEntityResult.getTransactionType());
        assertEquals(1L, actualTransactionToTransactionEntityResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionToTransactionEntityResult.getTransactionDateTime().toLocalDate().toString());
        WalletEntity originWallet2 = actualTransactionToTransactionEntityResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertSame(amount, originWallet2.getBalance());
        assertEquals("1", amount.toString());
        WalletEntity destinationWallet2 = actualTransactionToTransactionEntityResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        UserEntity user3 = destinationWallet2.getUser();
        assertTrue(user3.getWallet().isEmpty());
        assertEquals(1L, user3.getUserId());
        assertEquals("6625550144", user3.getPhoneNumber());
        assertEquals("Name", user3.getName());
        assertEquals("jane.doe@example.org", user3.getEmail());
        UserEntity user4 = originWallet2.getUser();
        assertEquals("Name", user4.getName());
        assertEquals(1L, user4.getUserId());
        assertTrue(user4.getWallet().isEmpty());
        assertEquals("jane.doe@example.org", user4.getEmail());
        assertEquals("6625550144", user4.getPhoneNumber());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionToTransactionEntity(Transaction)}
     */
    @Test
    void testTransactionToTransactionEntity5() {
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

        HashSet<Wallet> wallet2 = new HashSet<>();
        wallet2.add(wallet);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(wallet2);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user2);
        destinationWallet.setWalletId(1L);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user3);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        TransactionEntity actualTransactionToTransactionEntityResult = transactionMapper
                .transactionToTransactionEntity(transaction);
        BigDecimal expectedAmount = balance.ONE;
        BigDecimal amount = actualTransactionToTransactionEntityResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionToTransactionEntityResult.getTransactionType());
        assertEquals(1L, actualTransactionToTransactionEntityResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionToTransactionEntityResult.getTransactionDateTime().toLocalDate().toString());
        WalletEntity destinationWallet2 = actualTransactionToTransactionEntityResult.getDestinationWallet();
        assertSame(amount, destinationWallet2.getBalance());
        WalletEntity originWallet2 = actualTransactionToTransactionEntityResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertSame(amount, originWallet2.getBalance());
        assertEquals("1", amount.toString());
        assertEquals(1L, destinationWallet2.getWalletId());
        UserEntity user4 = destinationWallet2.getUser();
        assertEquals(1, user4.getWallet().size());
        assertEquals(1L, user4.getUserId());
        assertEquals("6625550144", user4.getPhoneNumber());
        assertEquals("Name", user4.getName());
        assertEquals("jane.doe@example.org", user4.getEmail());
        UserEntity user5 = originWallet2.getUser();
        assertEquals("Name", user5.getName());
        assertEquals(1L, user5.getUserId());
        assertTrue(user5.getWallet().isEmpty());
        assertEquals("jane.doe@example.org", user5.getEmail());
        assertEquals("6625550144", user5.getPhoneNumber());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionEntityToTransaction(TransactionEntity)}
     */
    @Test
    void testTransactionEntityToTransaction() {
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

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(1L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        Transaction actualTransactionEntityToTransactionResult = transactionMapper
                .transactionEntityToTransaction(transactionEntity);
        BigDecimal expectedAmount = balance.ONE;
        BigDecimal amount = actualTransactionEntityToTransactionResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionEntityToTransactionResult.getTransactionType());
        assertEquals(1L, actualTransactionEntityToTransactionResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionEntityToTransactionResult.getTransactionDateTime().toLocalDate().toString());
        Wallet originWallet2 = actualTransactionEntityToTransactionResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertEquals("1", amount.toString());
        assertSame(amount, originWallet2.getBalance());
        Wallet destinationWallet2 = actualTransactionEntityToTransactionResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        assertSame(amount, destinationWallet2.getBalance());
        User user = destinationWallet2.getUser();
        assertEquals(1L, user.getUserId());
        assertEquals("6625550144", user.getPhoneNumber());
        assertEquals("Name", user.getName());
        assertEquals("jane.doe@example.org", user.getEmail());
        User user2 = originWallet2.getUser();
        assertTrue(user2.getWallet().isEmpty());
        assertEquals("jane.doe@example.org", user2.getEmail());
        assertEquals("6625550144", user2.getPhoneNumber());
        assertEquals(1L, user2.getUserId());
        assertTrue(user.getWallet().isEmpty());
        assertEquals("Name", user2.getName());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionEntityToTransaction(TransactionEntity)}
     */
    @Test
    void testTransactionEntityToTransaction2() {
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

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(0L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        Transaction actualTransactionEntityToTransactionResult = transactionMapper
                .transactionEntityToTransaction(transactionEntity);
        BigDecimal expectedAmount = balance.ZERO;
        BigDecimal amount = actualTransactionEntityToTransactionResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionEntityToTransactionResult.getTransactionType());
        assertEquals(1L, actualTransactionEntityToTransactionResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionEntityToTransactionResult.getTransactionDateTime().toLocalDate().toString());
        Wallet originWallet2 = actualTransactionEntityToTransactionResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertEquals("0", amount.toString());
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = originWallet2.getBalance();
        assertSame(expectedBalance, balance2);
        Wallet destinationWallet2 = actualTransactionEntityToTransactionResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        assertSame(balance2, destinationWallet2.getBalance());
        User user = destinationWallet2.getUser();
        assertEquals(1L, user.getUserId());
        assertEquals("6625550144", user.getPhoneNumber());
        assertEquals("Name", user.getName());
        assertEquals("jane.doe@example.org", user.getEmail());
        User user2 = originWallet2.getUser();
        assertEquals("jane.doe@example.org", user2.getEmail());
        assertTrue(user2.getWallet().isEmpty());
        assertEquals("1", balance2.toString());
        assertEquals("6625550144", user2.getPhoneNumber());
        assertEquals(1L, user2.getUserId());
        assertTrue(user.getWallet().isEmpty());
        assertEquals("Name", user2.getName());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionEntityToTransaction(TransactionEntity)}
     */
    @Test
    void testTransactionEntityToTransaction3() {
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

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(null);
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        Transaction actualTransactionEntityToTransactionResult = transactionMapper
                .transactionEntityToTransaction(transactionEntity);
        assertNull(actualTransactionEntityToTransactionResult.getAmount());
        assertEquals("Transaction Type", actualTransactionEntityToTransactionResult.getTransactionType());
        assertEquals(1L, actualTransactionEntityToTransactionResult.getTransactionId());
        assertEquals("00:00",
                actualTransactionEntityToTransactionResult.getTransactionDateTime().toLocalTime().toString());
        Wallet destinationWallet2 = actualTransactionEntityToTransactionResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        Wallet originWallet2 = actualTransactionEntityToTransactionResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = originWallet2.getBalance();
        assertSame(expectedBalance, balance2);
        assertSame(balance2, destinationWallet2.getBalance());
        User user = originWallet2.getUser();
        assertTrue(user.getWallet().isEmpty());
        assertEquals(1L, user.getUserId());
        assertEquals("6625550144", user.getPhoneNumber());
        assertEquals("Name", user.getName());
        assertEquals("jane.doe@example.org", user.getEmail());
        User user2 = destinationWallet2.getUser();
        assertEquals("Name", user2.getName());
        assertEquals("jane.doe@example.org", user2.getEmail());
        assertEquals("1", balance2.toString());
        assertEquals(1L, user2.getUserId());
        assertTrue(user2.getWallet().isEmpty());
        assertEquals("6625550144", user2.getPhoneNumber());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionEntityToTransaction(TransactionEntity)}
     */
    @Test
    void testTransactionEntityToTransaction4() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());

        WalletEntity destinationWallet = new WalletEntity();
        destinationWallet.setBalance(null);
        destinationWallet.setUser(userEntity);
        destinationWallet.setWalletId(1L);

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

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(1L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        Transaction actualTransactionEntityToTransactionResult = transactionMapper
                .transactionEntityToTransaction(transactionEntity);
        BigDecimal expectedAmount = balance.ONE;
        BigDecimal amount = actualTransactionEntityToTransactionResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionEntityToTransactionResult.getTransactionType());
        assertEquals(1L, actualTransactionEntityToTransactionResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionEntityToTransactionResult.getTransactionDateTime().toLocalDate().toString());
        Wallet originWallet2 = actualTransactionEntityToTransactionResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertEquals("1", amount.toString());
        assertSame(amount, originWallet2.getBalance());
        Wallet destinationWallet2 = actualTransactionEntityToTransactionResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        User user = destinationWallet2.getUser();
        assertEquals(1L, user.getUserId());
        assertEquals("6625550144", user.getPhoneNumber());
        assertEquals("Name", user.getName());
        assertEquals("jane.doe@example.org", user.getEmail());
        User user2 = originWallet2.getUser();
        assertTrue(user2.getWallet().isEmpty());
        assertEquals("jane.doe@example.org", user2.getEmail());
        assertEquals("6625550144", user2.getPhoneNumber());
        assertEquals(1L, user2.getUserId());
        assertTrue(user.getWallet().isEmpty());
        assertEquals("Name", user2.getName());
    }

    /**
     * Method under test: {@link TransactionMapper#transactionEntityToTransaction(TransactionEntity)}
     */
    @Test
    void testTransactionEntityToTransaction5() {
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

        HashSet<WalletEntity> walletEntity2 = new HashSet<>();
        walletEntity2.add(walletEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setName("Name");
        userEntity2.setPhoneNumber("6625550144");
        userEntity2.setUserId(1L);
        userEntity2.setWallet(walletEntity2);

        WalletEntity destinationWallet = new WalletEntity();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setUser(userEntity2);
        destinationWallet.setWalletId(1L);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("jane.doe@example.org");
        userEntity3.setName("Name");
        userEntity3.setPhoneNumber("6625550144");
        userEntity3.setUserId(1L);
        userEntity3.setWallet(new HashSet<>());

        WalletEntity originWallet = new WalletEntity();
        BigDecimal balance = BigDecimal.valueOf(1L);
        originWallet.setBalance(balance);
        originWallet.setUser(userEntity3);
        originWallet.setWalletId(1L);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(1L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        Transaction actualTransactionEntityToTransactionResult = transactionMapper
                .transactionEntityToTransaction(transactionEntity);
        BigDecimal expectedAmount = balance.ONE;
        BigDecimal amount = actualTransactionEntityToTransactionResult.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals("Transaction Type", actualTransactionEntityToTransactionResult.getTransactionType());
        assertEquals(1L, actualTransactionEntityToTransactionResult.getTransactionId());
        assertEquals("1970-01-01",
                actualTransactionEntityToTransactionResult.getTransactionDateTime().toLocalDate().toString());
        Wallet originWallet2 = actualTransactionEntityToTransactionResult.getOriginWallet();
        assertEquals(1L, originWallet2.getWalletId());
        assertEquals("1", amount.toString());
        assertSame(amount, originWallet2.getBalance());
        Wallet destinationWallet2 = actualTransactionEntityToTransactionResult.getDestinationWallet();
        assertEquals(1L, destinationWallet2.getWalletId());
        assertSame(amount, destinationWallet2.getBalance());
        User user = destinationWallet2.getUser();
        assertEquals(1L, user.getUserId());
        assertEquals("6625550144", user.getPhoneNumber());
        assertEquals("Name", user.getName());
        assertEquals("jane.doe@example.org", user.getEmail());
        User user2 = originWallet2.getUser();
        assertTrue(user2.getWallet().isEmpty());
        assertEquals("jane.doe@example.org", user2.getEmail());
        assertEquals("6625550144", user2.getPhoneNumber());
        assertEquals(1L, user2.getUserId());
        assertEquals(1, user.getWallet().size());
        assertEquals("Name", user2.getName());
    }
}

