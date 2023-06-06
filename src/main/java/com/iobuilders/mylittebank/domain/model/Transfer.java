package com.iobuilders.mylittebank.domain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Transfer extends Transaction{

    @OneToOne
    @JoinColumn(name = "destination_wallet_id")
    private Wallet destinationAccount;

    public Wallet getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Wallet destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
