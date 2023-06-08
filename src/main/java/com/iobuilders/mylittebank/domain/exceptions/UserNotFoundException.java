package com.iobuilders.mylittebank.domain.exceptions;

public class UserNotFoundException extends MyLittleBankGenericException {
    public UserNotFoundException(Long userId) {
        super("The user provided has not been found --> "+userId);
    }}
