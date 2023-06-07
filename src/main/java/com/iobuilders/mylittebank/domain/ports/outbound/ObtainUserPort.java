package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;

public interface ObtainUserPort {
    void obtainUser(Long userId) throws UserNotFoundException;
}
