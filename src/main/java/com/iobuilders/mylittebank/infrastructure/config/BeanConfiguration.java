package com.iobuilders.mylittebank.infrastructure.config;

import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.domain.service.CreateWalletService;
import com.iobuilders.mylittebank.domain.service.MakeDepositService;
import com.iobuilders.mylittebank.domain.service.MakeTransferService;
import com.iobuilders.mylittebank.domain.service.RegisterUserService;
import com.iobuilders.mylittebank.infrastructure.mapper.TransactionMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.DepositPersistenceAdapter;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.TransferPersistenceAdapter;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.UserPersistenceAdapter;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.WalletPersistenceAdapter;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.TransactionRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.UserRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }

    @Bean
    public WalletMapper walletMapper(){
        return new WalletMapper();
    }

    @Bean
    public TransactionMapper transactionMapper(){
        return new TransactionMapper();
    }

    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        return new UserPersistenceAdapter(userRepository, userMapper);
    }

    @Bean
    public WalletPersistenceAdapter walletPersistenceAdapter(WalletRepository userRepository, WalletMapper walletMapper) {
        return new WalletPersistenceAdapter(userRepository, walletMapper);
    }

    @Bean
    public DepositPersistenceAdapter depositPersistenceAdapter(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        return new DepositPersistenceAdapter(transactionRepository, transactionMapper);
    }

    @Bean
    public TransferPersistenceAdapter transferPersistenceAdapter(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        return new TransferPersistenceAdapter(transactionRepository, transactionMapper);
    }


    @Bean
    public RegisterUserService registerUserService(RegisterUserPort registerUserPort) {
        return new RegisterUserService(registerUserPort);
    }

    @Bean
    public CreateWalletService createWalletService(CreateWalletPort createWalletPort) {
        return new CreateWalletService(createWalletPort);
    }

    @Bean
    public MakeDepositService makeDepositService(MakeDepositPort makeDepositPort) {
        return new MakeDepositService(makeDepositPort);
    }

    @Bean
    public MakeTransferService makeTransferService(MakeTransferPort makeTransferPort) {
        return new MakeTransferService(makeTransferPort);
    }
}
