package com.iobuilders.mylittebank.infrastructure.persistence.repository;

import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TransactionRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.iobuilders.mylittebank.infrastructure.persistence.entity"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @Disabled("TODO: Complete this test - Fails H2 constraint")
    void getTransactionEntitiesByOriginWalletOrDestinationWallet_ok() {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("example@unejemplo.es");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());

        WalletEntity destinationWallet = new WalletEntity();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setUser(userEntity);
        destinationWallet.setWalletId(1L);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("example@unejemplo.es");
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
        transactionEntity.setTransactionType("Transaction Type");

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("john.smith@example.org");
        userEntity3.setName("42");
        userEntity3.setPhoneNumber("8605550118");
        userEntity3.setUserId(2L);
        userEntity3.setWallet(new HashSet<>());

        WalletEntity destinationWallet2 = new WalletEntity();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setUser(userEntity3);
        destinationWallet2.setWalletId(2L);

        UserEntity userEntity4 = new UserEntity();
        userEntity4.setEmail("john.smith@example.org");
        userEntity4.setName("42");
        userEntity4.setPhoneNumber("8605550118");
        userEntity4.setUserId(2L);
        userEntity4.setWallet(new HashSet<>());

        WalletEntity originWallet2 = new WalletEntity();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setUser(userEntity4);
        originWallet2.setWalletId(2L);

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setAmount(BigDecimal.valueOf(1L));
        transactionEntity2.setDestinationWallet(destinationWallet2);
        transactionEntity2.setOriginWallet(originWallet2);
        transactionEntity2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity2.setTransactionType("42");
        transactionRepository.save(transactionEntity);
        transactionRepository.save(transactionEntity2);
        transactionRepository.getTransactionEntitiesByOriginWalletOrDestinationWallet(1L);
    }
}

