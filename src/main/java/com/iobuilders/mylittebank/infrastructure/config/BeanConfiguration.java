package com.iobuilders.mylittebank.infrastructure.config;

import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.domain.service.RegisterUserService;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
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

//    @Bean
//    public UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
//        return new UserPersistenceAdapter(userRepository, userMapper);
//    }

    @Bean
    public RegisterUserService registerUserService(RegisterUserPort registerUserPort) {
        return new RegisterUserService(registerUserPort);
    }

}
