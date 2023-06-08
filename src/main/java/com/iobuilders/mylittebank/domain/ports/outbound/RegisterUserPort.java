package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.model.User;

public interface RegisterUserPort {
    Long registerUser(User user);
}
