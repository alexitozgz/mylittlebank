package com.iobuilders.mylittebank.domain.repository;

import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;

public interface UserRepository {
    void saveUser (UserEntity userEntity);
}
