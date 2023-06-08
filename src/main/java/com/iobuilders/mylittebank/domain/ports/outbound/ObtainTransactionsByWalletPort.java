package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;

import java.util.List;

public interface ObtainTransactionsByWalletPort {
    List<Transaction> obtainTransactionsByWallet(Wallet wallet);
}
