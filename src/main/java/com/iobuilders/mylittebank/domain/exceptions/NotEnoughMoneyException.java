package com.iobuilders.mylittebank.domain.exceptions;

public class NotEnoughMoneyException extends Throwable {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
