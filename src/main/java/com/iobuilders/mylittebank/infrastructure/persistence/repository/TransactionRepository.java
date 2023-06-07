package com.iobuilders.mylittebank.infrastructure.persistence.repository;

import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    

    @Query("select t from TransactionEntity t " +
            "where t.originWallet.walletId = ?1 or t.destinationWallet.walletId = ?1 " +
            "order by t.transactionDateTime DESC")
    List<TransactionEntity> getTransactionEntitiesByOriginWalletOrDestinationWallet(long walletId);
}