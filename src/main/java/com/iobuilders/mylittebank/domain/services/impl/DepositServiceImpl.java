package com.iobuilders.mylittebank.domain.services.impl;

import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.services.DepositService;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.JpaWalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {
    private final JpaWalletRepository walletRepository;

    public DepositServiceImpl(JpaWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void makeDeposit(long accountId, BigDecimal amount) {
        Optional<Wallet> wallet = walletRepository.findById(accountId);
        if (wallet.isPresent()) {
/*
            wallet.deposit(amount);
            walletRepository.save(wallet);
*/
        } else {
/*
            throw new AccountNotFoundException("Account not found");
*/
        }
    }
}
