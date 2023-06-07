package com.iobuilders.mylittebank.application.dto;

import java.math.BigDecimal;

public class DepositRequest {
    private long walletId;
    private BigDecimal amount;

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
