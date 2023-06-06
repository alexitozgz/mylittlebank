package com.iobuilders.mylittebank.domain.services.impl;

import com.iobuilders.mylittebank.domain.services.BlockchainService;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;
//import your.package.path.generated.SampleContract; // Reemplaza con la ruta correcta del contrato generado

@Service
public class BlockchainServiceImpl implements BlockchainService {
    private final String rpcUrl = ""; // URL del nodo de Ethereum
    private final String contractAddress = ""; // Dirección del contrato en la blockchain
    private final String privateKey = ""; // Clave privada del propietario del contrato

//    public BlockchainServiceImpl(String rpcUrl, String contractAddress, String privateKey) {
//        this.rpcUrl = rpcUrl;
//        this.contractAddress = contractAddress;
//        this.privateKey = privateKey;
//    }

    @Override
    public void transferFromAtoB(String accountA, String accountB, int amount) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);

/*
        SampleContract contract = SampleContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
        contract.transfer(accountA, accountB, amount).send();
*/
    }

    @Override
    public Tuple2<Integer, Integer> getAccountBalances(String accountA, String accountB) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);

/*
        SampleContract contract = SampleContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
        Tuple2<Integer, Integer> balances = contract.getAccountBalances(accountA, accountB).send();
*/

        return null;// balances;
    }
}
