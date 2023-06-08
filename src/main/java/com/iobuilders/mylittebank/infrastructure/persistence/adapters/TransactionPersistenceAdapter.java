package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.infrastructure.mapper.TransactionMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionPersistenceAdapter implements MakeTransferPort, MakeDepositPort, ObtainTransactionsByWalletPort {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    public TransactionPersistenceAdapter(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Long createDeposit(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.transactionToTransactionEntity(transaction);
        transactionEntity.setTransactionDateTime(LocalDateTime.now());
        return transactionRepository.save(transactionEntity).getTransactionId();
    }

    @Override
    public Long createTransfer(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.transactionToTransactionEntity(transaction);
        transactionEntity.setTransactionDateTime(LocalDateTime.now());
        return transactionRepository.save(transactionEntity).getTransactionId();
    }

    @Override
    public List<Transaction> obtainTransactionsByWallet(Wallet wallet) {
        List<Transaction> transactionList = transactionRepository.getTransactionEntitiesByOriginWalletOrDestinationWallet(wallet.getWalletId())
                                                .stream()
                                                .map(transactionEntity -> transactionMapper.transactionEntityToTransaction(transactionEntity))
                                                .collect(Collectors.toList());
        return transactionList;
    }
}
