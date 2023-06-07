package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    Wallet createWallet(User user) throws UserNotFoundException;
    BigDecimal getWalletBalance(long accountId) throws WalletNotFoundException;
    List<Transaction> getAccountTransactions(long accountId) throws WalletNotFoundException;
}
