package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.model.enumerations.TransactionType;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;

import java.math.BigDecimal;

public class MakeTransferService implements MakeTransferUseCase {

    private final MakeTransferPort makeTransferPort;
    private final ObtainWalletPort obtainWalletPort;
    private final UpdateWalletPort updateWalletPort;


    public MakeTransferService(MakeTransferPort makeTransferPort, ObtainWalletPort obtainWalletPort, UpdateWalletPort updateWalletPort) {
        this.makeTransferPort = makeTransferPort;
        this.obtainWalletPort = obtainWalletPort;
        this.updateWalletPort = updateWalletPort;
    }

    @Override
    public void makeTransfer(Transaction transaction) throws UserNotFoundException, NotEnoughMoneyException {
        Wallet wallet = obtainWalletPort.obtainWalletPort(transaction.getDestinationWallet().getWalletId());
        Wallet originWallet = obtainWalletPort.obtainWalletPort(transaction.getOriginWallet().getWalletId());
        hasOriginBalanceEnough(originWallet.getBalance(), transaction.getAmount());
        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        originWallet.setBalance(originWallet.getBalance().subtract(transaction.getAmount()));
        transaction.setTransactionType(TransactionType.TRANSFER.name());
        makeTransferPort.createTransfer(transaction);
        updateWalletPort.updateWallet(wallet);
        updateWalletPort.updateWallet(originWallet);
    }

    private void hasOriginBalanceEnough(BigDecimal balance, BigDecimal amount) throws NotEnoughMoneyException {
        if (amount.compareTo(balance) > 1){
            throw new NotEnoughMoneyException("Destination account has not money enough to make a transfer with this amount");
        }
    }
}
