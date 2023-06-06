package com.iobuilders.mylittebank.interfaces.rest.dto;

import java.math.BigDecimal;

public class DepositRequest {
    private long accountId;
    private BigDecimal amount;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
