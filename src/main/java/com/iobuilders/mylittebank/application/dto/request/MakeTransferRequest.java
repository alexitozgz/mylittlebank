package com.iobuilders.mylittebank.application.dto.request;

import javax.validation.constraints.NotNull;

public class MakeTransferRequest extends MakeDepositRequest {
    @NotNull
    private long originWalletId;

    public long getOriginWalletId() {
        return originWalletId;
    }

    public void setOriginWalletId(long originWalletId) {
        this.originWalletId = originWalletId;
    }
}
