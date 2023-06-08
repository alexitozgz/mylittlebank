package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserEntityTest {
    @Test
    void testConstructor() {
        UserEntity actualUserEntity = new UserEntity();
        actualUserEntity.setEmail("example@unejemplo.es");
        actualUserEntity.setName("Name");
        actualUserEntity.setPhoneNumber("6625550144");
        actualUserEntity.setUserId(1L);
        HashSet<WalletEntity> walletEntity = new HashSet<>();
        actualUserEntity.setWallet(walletEntity);
        assertEquals("example@unejemplo.es", actualUserEntity.getEmail());
        assertEquals("Name", actualUserEntity.getName());
        assertEquals("6625550144", actualUserEntity.getPhoneNumber());
        assertEquals(1L, actualUserEntity.getUserId());
        assertSame(walletEntity, actualUserEntity.getWallet());
    }
}

