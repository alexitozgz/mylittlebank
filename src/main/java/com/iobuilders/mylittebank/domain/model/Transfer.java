package com.iobuilders.mylittebank.domain.model;

import java.math.BigDecimal;

public class Transfer extends Transaction{

    private Wallet destinationWallet;

    public Transfer(long walletId, long destinationWalletId, BigDecimal amount) {
        super();
        Wallet wallet = new Wallet();
        wallet.setWalletId(walletId);
        super.setWallet(wallet);
        super.setAmount(amount);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setWalletId(destinationWalletId);
        this.setDestinationWallet(destinationWallet);
    }


    public Wallet getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(Wallet destinationWallet) {
        this.destinationWallet = destinationWallet;
    }
}
