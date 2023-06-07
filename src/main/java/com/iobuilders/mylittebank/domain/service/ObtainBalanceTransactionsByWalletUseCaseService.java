package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.ObtainBalanceTransactionsByWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;

import java.util.List;

public class ObtainBalanceTransactionsByWalletUseCaseService implements ObtainBalanceTransactionsByWalletUseCase {

    private final ObtainTransactionsByWalletPort obtainTransactionsByWalletPort;
    private final ObtainWalletPort obtainWalletPort;


    public ObtainBalanceTransactionsByWalletUseCaseService(ObtainTransactionsByWalletPort obtainTransactionsByWalletPort, ObtainWalletPort obtainWalletPort) {
        this.obtainTransactionsByWalletPort = obtainTransactionsByWalletPort;
        this.obtainWalletPort = obtainWalletPort;
    }


    @Override
    public Wallet obtainBalanceTransactionsByWallet(Long walletId) throws UserNotFoundException {
        Wallet wallet = obtainWalletPort.obtainWalletPort(walletId);

        List<Transaction> transactionList = obtainTransactionsByWalletPort.obtainTransactionsByWalletPort(wallet);

        wallet.setTransactionList(transactionList);
        return wallet;
    }
}
