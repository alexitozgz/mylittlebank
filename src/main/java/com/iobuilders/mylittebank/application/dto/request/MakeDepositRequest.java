package com.iobuilders.mylittebank.application.dto.request;

import java.math.BigDecimal;

public class MakeDepositRequest {

    private long destinationWalletId;
    private BigDecimal amount;

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
