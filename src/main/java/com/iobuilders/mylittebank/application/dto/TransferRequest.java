package com.iobuilders.mylittebank.application.dto;

import java.math.BigDecimal;

public class TransferRequest {
    private long walletId;
    private long destinationWalletId;
    private BigDecimal amount;

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public long getDestinationWalletId() {
        return destinationWalletId;
    }

    public void setDestinationWalletId(long destinationWalletId) {
        this.destinationWalletId = destinationWalletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
