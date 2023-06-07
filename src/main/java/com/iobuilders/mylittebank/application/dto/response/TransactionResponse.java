package com.iobuilders.mylittebank.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    private long transactionId;
    private long destinationWallet;
    private long originWallet;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(long destinationWallet) {
        this.destinationWallet = destinationWallet;
    }

    public long getOriginWallet() {
        return originWallet;
    }

    public void setOriginWallet(long originWallet) {
        this.originWallet = originWallet;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
