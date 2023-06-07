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

import java.util.List;
import java.util.stream.Collectors;

public class TransactionPersistenceAdapter implements MakeTransferPort, MakeDepositPort, ObtainTransactionsByWalletPort {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletMapper walletMapper;


    public TransactionPersistenceAdapter(TransactionRepository transactionRepository, TransactionMapper transactionMapper, WalletMapper walletMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.walletMapper = walletMapper;
    }

    @Override
    public void createDeposit(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.transactionToTransactionEntity(transaction);
        transactionRepository.save(transactionEntity);
    }

    @Override
    public void createTransfer(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.transactionToTransactionEntity(transaction);
        transactionRepository.save(transactionEntity);
    }

    @Override
    public List<Transaction> obtainTransactionsByWalletPort(Wallet wallet) {
//        WalletEntity walletEntity = walletMapper.toWalletEntity(wallet);
        List<Transaction> transactionList = transactionRepository.getTransactionEntitiesByOriginWalletOrDestinationWallet(wallet.getWalletId())
                                                .stream()
                                                .map(transactionEntity -> transactionMapper.transactionEntityToTransaction(transactionEntity))
                                                .collect(Collectors.toList());
        return transactionList;
    }
}
