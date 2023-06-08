package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;

public interface ObtainUserPort {
    void existsUser(Long userId) throws UserNotFoundException;
}
