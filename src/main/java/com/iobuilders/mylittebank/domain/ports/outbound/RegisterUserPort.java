package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.model.User;

public interface RegisterUserPort {
    void registerUser(User user);
}
