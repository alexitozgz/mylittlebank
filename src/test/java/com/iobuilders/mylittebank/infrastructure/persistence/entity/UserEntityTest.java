package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserEntityTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link UserEntity}
     *   <li>{@link UserEntity#setEmail(String)}
     *   <li>{@link UserEntity#setName(String)}
     *   <li>{@link UserEntity#setPhoneNumber(String)}
     *   <li>{@link UserEntity#setUserId(long)}
     *   <li>{@link UserEntity#setWallet(Set)}
     *   <li>{@link UserEntity#getEmail()}
     *   <li>{@link UserEntity#getName()}
     *   <li>{@link UserEntity#getPhoneNumber()}
     *   <li>{@link UserEntity#getUserId()}
     *   <li>{@link UserEntity#getWallet()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserEntity actualUserEntity = new UserEntity();
        actualUserEntity.setEmail("jane.doe@example.org");
        actualUserEntity.setName("Name");
        actualUserEntity.setPhoneNumber("6625550144");
        actualUserEntity.setUserId(1L);
        HashSet<WalletEntity> walletEntity = new HashSet<>();
        actualUserEntity.setWallet(walletEntity);
        assertEquals("jane.doe@example.org", actualUserEntity.getEmail());
        assertEquals("Name", actualUserEntity.getName());
        assertEquals("6625550144", actualUserEntity.getPhoneNumber());
        assertEquals(1L, actualUserEntity.getUserId());
        assertSame(walletEntity, actualUserEntity.getWallet());
    }
}

