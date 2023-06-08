package com.iobuilders.mylittebank.application.dto.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MakeDepositRequest {

    @NotNull
    private long destinationWalletId;
    @NotNull
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
