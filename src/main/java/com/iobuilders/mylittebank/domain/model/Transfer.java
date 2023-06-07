package com.iobuilders.mylittebank.domain.model;

public class Transfer extends Transaction{

    private Wallet destinationAccount;

    public Wallet getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Wallet destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
