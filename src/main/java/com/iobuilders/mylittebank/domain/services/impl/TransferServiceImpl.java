package com.iobuilders.mylittebank.domain.services.impl;

import com.iobuilders.mylittebank.domain.services.TransferService;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.JpaWalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService {
    private final JpaWalletRepository walletRepository;

    public TransferServiceImpl(JpaWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void makeTransfer(long sourceAccountId, long destintionAccountId, BigDecimal amount) {
/*
        Account sourceAccount = accountRepository.findById(sourceAccountId);
        Account destinationAccount = accountRepository.findById(destinationAccountId);

        if (sourceAccount != null && destinationAccount != null) {
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);

            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
        } else {
            throw new AccountNotFoundException("Account not found");
        }
*/
    }
}