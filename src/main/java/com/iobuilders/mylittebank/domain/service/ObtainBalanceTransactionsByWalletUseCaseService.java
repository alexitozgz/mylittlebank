package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.ObtainBalanceTransactionsByWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ObtainBalanceTransactionsByWalletUseCaseService implements ObtainBalanceTransactionsByWalletUseCase {

    private final ObtainTransactionsByWalletPort obtainTransactionsByWalletPort;
    private final ObtainWalletPort obtainWalletPort;


    public ObtainBalanceTransactionsByWalletUseCaseService(ObtainTransactionsByWalletPort obtainTransactionsByWalletPort, ObtainWalletPort obtainWalletPort) {
        this.obtainTransactionsByWalletPort = obtainTransactionsByWalletPort;
        this.obtainWalletPort = obtainWalletPort;
    }


    @Override
    public Wallet obtainBalanceTransactionsByWallet(Long walletId) throws WalletNotFoundException {
        log.debug("Starting domain service obtainBalanceTransactionsByWallet with walletId {}", walletId);

        Wallet wallet = obtainWalletPort.obtainWallet(walletId);
        log.debug("Wallet obtained {}", wallet);

        List<Transaction> transactionList = obtainTransactionsByWalletPort.obtainTransactionsByWallet(wallet);
        wallet.setTransactionList(transactionList);
        log.debug("Transactions obtained by walletId {} --> {}", walletId, transactionList);

        return wallet;
    }
}
