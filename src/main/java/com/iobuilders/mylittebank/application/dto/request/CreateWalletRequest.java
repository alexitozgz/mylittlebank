package com.iobuilders.mylittebank.application.dto.request;

import javax.validation.constraints.NotNull;

public class CreateWalletRequest {
    @NotNull
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
