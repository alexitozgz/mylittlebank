package com.iobuilders.mylittebank.infrastructure.persistence.repository;

import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataSQLUserRepository extends JpaRepository<UserEntity, Long> {
}
