package com.iobuilders.mylittebank.domain.services.impl;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.services.WalletService;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.JpaTransactionRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.JpaUserRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.JpaWalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    private final JpaUserRepository userRepository;
    private final JpaWalletRepository walletRepository;
    private final JpaTransactionRepository transactionRepository;

    public WalletServiceImpl(JpaUserRepository userRepository, JpaWalletRepository walletRepository,
                             JpaTransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Wallet createWallet(User userParam) throws UserNotFoundException {
        User user = userRepository.findById(userParam.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(new BigDecimal(0));

        return walletRepository.save(wallet);
    }

/*
    @Override
    public void makeDeposit(Long accountId, double amount) {
        Account account = walletRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));

        account.setBalance(account.getBalance() + amount);

        Deposit deposit = new Deposit();
        deposit.setAccount(account);
        deposit.setAmount(amount);
        deposit.setDateTime(LocalDateTime.now());

        transactionRepository.save(deposit);
    }
*/

    @Override
    public BigDecimal getWalletBalance(long walletId) throws WalletNotFoundException {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        return wallet.getBalance();
    }

    @Override
    public List<Transaction> getAccountTransactions(long accountId) throws WalletNotFoundException {
        Wallet wallet = walletRepository.findById(accountId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        return transactionRepository.findByWallet(wallet);
    }

/*
    @Override
    public void makeTransfer(Long sourceAccountId, Long destinationAccountId, double amount) {
        Account sourceAccount = walletRepository.findById(sourceAccountId)
                .orElseThrow(() -> new NotFoundException("Source account not found"));

        Account destinationAccount = walletRepository.findById(destinationAccountId)
                .orElseThrow(() -> new NotFoundException("Destination account not found"));

        if (sourceAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in the source account");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        Transfer transfer = new Transfer();
        transfer.setAccount(sourceAccount);
        transfer.setDestinationAccount(destinationAccount);
        transfer.setAmount(amount);
        transfer.setDateTime(LocalDateTime.now());

        transactionRepository.save(transfer);
    }
*/
}
