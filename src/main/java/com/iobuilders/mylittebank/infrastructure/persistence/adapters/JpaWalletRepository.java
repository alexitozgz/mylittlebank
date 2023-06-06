package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import com.iobuilders.mylittebank.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaWalletRepository extends JpaRepository<Wallet, Long> {
}