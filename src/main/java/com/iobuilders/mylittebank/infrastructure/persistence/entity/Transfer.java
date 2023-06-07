package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Transfer extends Transaction {

    @OneToOne
    @JoinColumn(name = "destination_wallet_id")
    private WalletEntity destinationAccount;

    public WalletEntity getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(WalletEntity destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
