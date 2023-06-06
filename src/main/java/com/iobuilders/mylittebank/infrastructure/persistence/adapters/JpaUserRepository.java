package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import com.iobuilders.mylittebank.domain.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<BankUser, Long> {
}
