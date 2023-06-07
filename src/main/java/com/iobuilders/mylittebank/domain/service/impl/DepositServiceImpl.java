package com.iobuilders.mylittebank.domain.service.impl;

import com.iobuilders.mylittebank.domain.service.DepositService;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.JpaWalletRepository;

import java.math.BigDecimal;

//@Service
public class DepositServiceImpl implements DepositService {
    private final JpaWalletRepository walletRepository;

    public DepositServiceImpl(JpaWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void makeDeposit(long accountId, BigDecimal amount) {
/*
        Optional<Wallet> wallet = walletRepository.findById(accountId);
        if (wallet.isPresent()) {
            wallet.deposit(amount);
            walletRepository.save(wallet);
        } else {
            throw new AccountNotFoundException("Account not found");
        }
*/
    }
}