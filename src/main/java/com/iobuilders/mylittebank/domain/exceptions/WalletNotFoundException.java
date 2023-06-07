package com.iobuilders.mylittebank.domain.exceptions;

public class WalletNotFoundException extends Exception {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
