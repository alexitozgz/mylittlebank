package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    /**
     * Method under test: {@link TransactionPersistenceAdapter#TransactionPersistenceAdapter(TransactionRepository, TransactionMapper, WalletMapper)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TransactionPersistenceAdapter.transactionMapper
        //     TransactionPersistenceAdapter.transactionRepository
        //     TransactionPersistenceAdapter.walletMapper

        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        TransactionMapper transactionMapper = new TransactionMapper();
        new TransactionPersistenceAdapter(transactionRepository, transactionMapper, new WalletMapper());

    }

    /**
     * Method under test: {@link TransactionPersistenceAdapter#createDeposit(Transaction)}
     */
    @Test
    void testCreateDeposit() {
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
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setUser(userEntity2);
        originWallet.setWalletId(1L);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(1L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        when(transactionRepository.save(Mockito.<TransactionEntity>any())).thenReturn(transactionEntity);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("jane.doe@example.org");
        userEntity3.setName("Name");
        userEntity3.setPhoneNumber("6625550144");
        userEntity3.setUserId(1L);
        userEntity3.setWallet(new HashSet<>());

        WalletEntity destinationWallet2 = new WalletEntity();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setUser(userEntity3);
        destinationWallet2.setWalletId(1L);

        UserEntity userEntity4 = new UserEntity();
        userEntity4.setEmail("jane.doe@example.org");
        userEntity4.setName("Name");
        userEntity4.setPhoneNumber("6625550144");
        userEntity4.setUserId(1L);
        userEntity4.setWallet(new HashSet<>());

        WalletEntity originWallet2 = new WalletEntity();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setUser(userEntity4);
        originWallet2.setWalletId(1L);

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setAmount(BigDecimal.valueOf(1L));
        transactionEntity2.setDestinationWallet(destinationWallet2);
        transactionEntity2.setOriginWallet(originWallet2);
        transactionEntity2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity2.setTransactionId(1L);
        transactionEntity2.setTransactionType("Transaction Type");
        when(transactionMapper.transactionToTransactionEntity(Mockito.<Transaction>any())).thenReturn(transactionEntity2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet3 = new Wallet();
        destinationWallet3.setBalance(BigDecimal.valueOf(1L));
        destinationWallet3.setTransactionList(new ArrayList<>());
        destinationWallet3.setUser(user);
        destinationWallet3.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet3 = new Wallet();
        originWallet3.setBalance(BigDecimal.valueOf(1L));
        originWallet3.setTransactionList(new ArrayList<>());
        originWallet3.setUser(user2);
        originWallet3.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet3);
        transaction.setOriginWallet(originWallet3);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        transactionPersistenceAdapter.createDeposit(transaction);
        verify(transactionRepository).save(Mockito.<TransactionEntity>any());
        verify(transactionMapper).transactionToTransactionEntity(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionPersistenceAdapter#createTransfer(Transaction)}
     */
    @Test
    void testCreateTransfer() {
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
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setUser(userEntity2);
        originWallet.setWalletId(1L);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(1L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionId(1L);
        transactionEntity.setTransactionType("Transaction Type");
        when(transactionRepository.save(Mockito.<TransactionEntity>any())).thenReturn(transactionEntity);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("jane.doe@example.org");
        userEntity3.setName("Name");
        userEntity3.setPhoneNumber("6625550144");
        userEntity3.setUserId(1L);
        userEntity3.setWallet(new HashSet<>());

        WalletEntity destinationWallet2 = new WalletEntity();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setUser(userEntity3);
        destinationWallet2.setWalletId(1L);

        UserEntity userEntity4 = new UserEntity();
        userEntity4.setEmail("jane.doe@example.org");
        userEntity4.setName("Name");
        userEntity4.setPhoneNumber("6625550144");
        userEntity4.setUserId(1L);
        userEntity4.setWallet(new HashSet<>());

        WalletEntity originWallet2 = new WalletEntity();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setUser(userEntity4);
        originWallet2.setWalletId(1L);

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setAmount(BigDecimal.valueOf(1L));
        transactionEntity2.setDestinationWallet(destinationWallet2);
        transactionEntity2.setOriginWallet(originWallet2);
        transactionEntity2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity2.setTransactionId(1L);
        transactionEntity2.setTransactionType("Transaction Type");
        when(transactionMapper.transactionToTransactionEntity(Mockito.<Transaction>any())).thenReturn(transactionEntity2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet3 = new Wallet();
        destinationWallet3.setBalance(BigDecimal.valueOf(1L));
        destinationWallet3.setTransactionList(new ArrayList<>());
        destinationWallet3.setUser(user);
        destinationWallet3.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet3 = new Wallet();
        originWallet3.setBalance(BigDecimal.valueOf(1L));
        originWallet3.setTransactionList(new ArrayList<>());
        originWallet3.setUser(user2);
        originWallet3.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet3);
        transaction.setOriginWallet(originWallet3);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        transactionPersistenceAdapter.createTransfer(transaction);
        verify(transactionRepository).save(Mockito.<TransactionEntity>any());
        verify(transactionMapper).transactionToTransactionEntity(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionPersistenceAdapter#obtainTransactionsByWalletPort(Wallet)}
     */
    @Test
    void testObtainTransactionsByWalletPort() {
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
        assertTrue(transactionPersistenceAdapter.obtainTransactionsByWalletPort(wallet).isEmpty());
    }

    /**
     * Method under test: {@link TransactionPersistenceAdapter#obtainTransactionsByWalletPort(Wallet)}
     */
    @Test
    void testObtainTransactionsByWalletPort2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(0L));
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user);
        wallet.setWalletId(1L);
        assertTrue(transactionPersistenceAdapter.obtainTransactionsByWalletPort(wallet).isEmpty());
    }
}

