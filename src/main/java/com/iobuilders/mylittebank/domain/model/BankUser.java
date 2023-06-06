package com.iobuilders.mylittebank.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class BankUser {

    @Id
    private long userId;
    private String name;
    private String phoneNumber;
    private String email;

    @OneToMany (mappedBy = "bankUser")
    private Set<Wallet> wallet;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Wallet> getWallet() {
        return wallet;
    }

    public void setWallet(Set<Wallet> wallet) {
        this.wallet = wallet;
    }
}
