package com.iobuilders.mylittebank.infrastructure.config;

import com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeDepositRequestMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeTransferRequestMapper;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainUserPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;
import com.iobuilders.mylittebank.domain.service.CreateWalletService;
import com.iobuilders.mylittebank.domain.service.MakeDepositService;
import com.iobuilders.mylittebank.domain.service.MakeTransferService;
import com.iobuilders.mylittebank.domain.service.ObtainBalanceTransactionsByWalletUseCaseService;
import com.iobuilders.mylittebank.domain.service.RegisterUserService;
import com.iobuilders.mylittebank.infrastructure.mapper.TransactionMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.adapters.TransactionPersistenceAdapter;
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
    public BalanceTransactionsByWalletResponseMapper balanceTransactionsByWalletResponseMapper(){
        return new BalanceTransactionsByWalletResponseMapper();
    }


    @Bean
    public MakeTransferRequestMapper makeTransferRequestMapper(){
        return new MakeTransferRequestMapper();
    }

    @Bean
    public MakeDepositRequestMapper makeDepositRequestMapper(){
        return new MakeDepositRequestMapper();
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
    public TransactionPersistenceAdapter depositPersistenceAdapter(TransactionRepository transactionRepository, TransactionMapper transactionMapper, WalletMapper walletMapper) {
        return new TransactionPersistenceAdapter(transactionRepository, transactionMapper, walletMapper);
    }

    @Bean
    public RegisterUserService registerUserService(RegisterUserPort registerUserPort) {
        return new RegisterUserService(registerUserPort);
    }

    @Bean
    public CreateWalletService createWalletService(CreateWalletPort createWalletPort, ObtainUserPort obtainUserPort) {
        return new CreateWalletService(createWalletPort, obtainUserPort);
    }

    @Bean
    public MakeDepositService makeDepositService(MakeDepositPort makeDepositPort, ObtainWalletPort obtainWalletPort, UpdateWalletPort updateWalletPort) {
        return new MakeDepositService(makeDepositPort, obtainWalletPort, updateWalletPort);
    }

    @Bean
    public MakeTransferService makeTransferService(MakeTransferPort makeTransferPort, ObtainWalletPort obtainWalletPort, UpdateWalletPort updateWalletPort) {
        return new MakeTransferService(makeTransferPort, obtainWalletPort, updateWalletPort);
    }

    @Bean
    public ObtainBalanceTransactionsByWalletUseCaseService obtainBalanceTransactionsByWalletUseCaseService(ObtainTransactionsByWalletPort obtainTransactionsByWalletPort, ObtainWalletPort obtainWalletPort){
        return new ObtainBalanceTransactionsByWalletUseCaseService(obtainTransactionsByWalletPort, obtainWalletPort);
    }

}
