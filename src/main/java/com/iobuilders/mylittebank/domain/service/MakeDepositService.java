package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.model.enumerations.TransactionType;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeDepositUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;

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
    public void makeDeposit(Transaction transaction) throws UserNotFoundException {
        Wallet wallet = obtainWalletPort.obtainWalletPort(transaction.getDestinationWallet().getWalletId());
        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        transaction.setTransactionType(TransactionType.DEPOSIT.name());
        makeDepositPort.createDeposit(transaction);
        updateWalletPort.updateWallet(wallet);
    }
}
