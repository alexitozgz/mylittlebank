package com.iobuilders.mylittebank.infrastructure.config;

import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.domain.service.CreateWalletService;
import com.iobuilders.mylittebank.domain.service.RegisterUserService;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.UserPersistenceAdapter;
import com.iobuilders.mylittebank.infrastructure.persistence.WalletPersistenceAdapter;
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
    public UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        return new UserPersistenceAdapter(userRepository, userMapper);
    }

    @Bean
    public WalletPersistenceAdapter walletPersistenceAdapter(WalletRepository userRepository, WalletMapper walletMapper) {
        return new WalletPersistenceAdapter(userRepository, walletMapper);
    }


    @Bean
    public RegisterUserService registerUserService(RegisterUserPort registerUserPort) {
        return new RegisterUserService(registerUserPort);
    }

    @Bean
    public CreateWalletService createWalletService(CreateWalletPort createWalletPort) {
        return new CreateWalletService(createWalletPort);
    }
}
