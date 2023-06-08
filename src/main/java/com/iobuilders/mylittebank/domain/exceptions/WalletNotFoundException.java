package com.iobuilders.mylittebank.domain.exceptions;

public class WalletNotFoundException extends MyLittleBankGenericException {

    public WalletNotFoundException(Long walletId) {
        super("The wallet provided has not been found --> "+walletId);
    }
}
