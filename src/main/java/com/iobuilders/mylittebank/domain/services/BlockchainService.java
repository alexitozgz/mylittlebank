package com.iobuilders.mylittebank.domain.services;

import org.web3j.tuples.generated.Tuple2;

public interface BlockchainService {
    void transferFromAtoB(String accountA, String accountB, int amount) throws Exception;

    Tuple2<Integer, Integer> getAccountBalances(String accountA, String accountB) throws Exception;
}
