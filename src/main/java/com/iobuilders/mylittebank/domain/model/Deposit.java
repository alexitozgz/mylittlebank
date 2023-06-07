package com.iobuilders.mylittebank.domain.model;

import java.math.BigDecimal;

public class Deposit extends Transaction{

    public Deposit(long walletId, BigDecimal amount) {
        super();
        Wallet wallet = new Wallet();
        wallet.setWalletId(walletId);
        super.setWallet(wallet);
        super.setAmount(amount);
    }
}
