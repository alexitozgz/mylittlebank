package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.model.enumerations.TransactionType;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
@Slf4j
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
    public Long makeTransfer(Transaction transaction) throws NotEnoughMoneyException, WalletNotFoundException {
        log.debug("Starting domain service makeTransfer with transaction {}", transaction);
        Wallet wallet = obtainWalletPort.obtainWallet(transaction.getDestinationWallet().getWalletId());
        Wallet originWallet = obtainWalletPort.obtainWallet(transaction.getOriginWallet().getWalletId());

        hasOriginBalanceEnough(originWallet.getBalance(), transaction.getAmount());
        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        originWallet.setBalance(originWallet.getBalance().subtract(transaction.getAmount()));

        log.debug("Destination wallet obtained {} with updated balance to {}", wallet.getWalletId(),wallet.getBalance());
        log.debug("Origin wallet obtained {} with updated balance to {}", originWallet.getWalletId(),originWallet.getBalance());

        transaction.setTransactionType(TransactionType.TRANSFER.name());
        Long idTransfer = makeTransferPort.createTransfer(transaction);
        updateWalletPort.updateWallet(wallet);
        updateWalletPort.updateWallet(originWallet);

        log.debug("Wallets updated and transfer created with id {}", idTransfer);

        return idTransfer;
    }

    private void hasOriginBalanceEnough(BigDecimal balance, BigDecimal amount) throws NotEnoughMoneyException {
        if (amount.compareTo(balance) > 0){
            throw new NotEnoughMoneyException(balance, amount);
        }
    }
}
