package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public abstract class Transaction {

    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletEntity walletEntity;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WalletEntity getWallet() {
        return walletEntity;
    }

    public void setWallet(WalletEntity walletEntity) {
        this.walletEntity = walletEntity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
