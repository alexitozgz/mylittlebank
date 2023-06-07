package com.iobuilders.mylittebank.application.dto.request;

public class MakeTransferRequest extends MakeDepositRequest {
    private long originWalletId;

    public long getOriginWalletId() {
        return originWalletId;
    }

    public void setOriginWalletId(long originWalletId) {
        this.originWalletId = originWalletId;
    }
}
