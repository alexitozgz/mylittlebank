package com.iobuilders.mylittebank.application.dto.request;

import javax.validation.constraints.NotNull;

public class ObtainBalanceTransactionsByWalletRequest {
    @NotNull
    private long walletId;

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }
}
