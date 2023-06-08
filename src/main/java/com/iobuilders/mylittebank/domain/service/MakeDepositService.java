package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.model.enumerations.TransactionType;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeDepositUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MakeDepositService implements MakeDepositUseCase {

    private final MakeDepositPort makeDepositPort;
    private final ObtainWalletPort obtainWalletPort;
    private final UpdateWalletPort updateWalletPort;

    public MakeDepositService(MakeDepositPort makeDepositPort, ObtainWalletPort obtainWalletPort, UpdateWalletPort updateWalletPort) {
        this.makeDepositPort = makeDepositPort;
        this.obtainWalletPort = obtainWalletPort;
        this.updateWalletPort = updateWalletPort;
    }

    @Override
    public Long makeDeposit(Transaction transaction) throws WalletNotFoundException {
        log.debug("Starting domain service makeDeposit with transaction {}", transaction);
        Wallet wallet = obtainWalletPort.obtainWallet(transaction.getDestinationWallet().getWalletId());
        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        log.debug("Wallet obtained {} with updated balance to {}", wallet.getWalletId(),wallet.getBalance());
        transaction.setTransactionType(TransactionType.DEPOSIT.name());
        Long idDeposit = makeDepositPort.createDeposit(transaction);
        updateWalletPort.updateWallet(wallet);
        log.debug("Wallets updated and deposit created with id ", idDeposit);
        return idDeposit;
    }
}
