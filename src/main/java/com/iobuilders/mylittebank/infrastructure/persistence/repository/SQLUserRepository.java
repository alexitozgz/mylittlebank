package com.iobuilders.mylittebank.infrastructure.persistence.repository;

import com.iobuilders.mylittebank.domain.repository.UserRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SQLUserRepository implements UserRepository {

    private final SpringDataSQLUserRepository springDataSQLUserRepository;

    @Autowired
    public SQLUserRepository(SpringDataSQLUserRepository springDataSQLUserRepository) {
        this.springDataSQLUserRepository = springDataSQLUserRepository;
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        springDataSQLUserRepository.save(userEntity);
    }
}
