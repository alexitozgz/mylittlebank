package com.iobuilders.mylittebank.infrastructure.persistence.repository;

import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TransactionRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.iobuilders.mylittebank.infrastructure.persistence.entity"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Method under test: {@link TransactionRepository#getTransactionEntitiesByOriginWalletOrDestinationWallet(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTransactionEntitiesByOriginWalletOrDestinationWallet() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint [FKDUUVOX0PC6EURJOBAKXG63P24]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement
        //       at com.sun.proxy.$Proxy131.getTransactionEntitiesByOriginWalletOrDestinationWallet(null)
        //   org.hibernate.exception.ConstraintViolationException: could not execute statement
        //       at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:59)
        //       at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:37)
        //       at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:113)
        //       at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:99)
        //       at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:200)
        //       at org.hibernate.engine.jdbc.batch.internal.NonBatchingBatch.addToBatch(NonBatchingBatch.java:46)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3375)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3937)
        //       at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:107)
        //       at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:604)
        //       at org.hibernate.engine.spi.ActionQueue.lambda$executeActions$1(ActionQueue.java:478)
        //       at java.util.LinkedHashMap.forEach(LinkedHashMap.java:684)
        //       at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:475)
        //       at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:344)
        //       at org.hibernate.event.internal.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:57)
        //       at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
        //       at org.hibernate.internal.SessionImpl.autoFlushIfRequired(SessionImpl.java:1372)
        //       at org.hibernate.internal.SessionImpl.list(SessionImpl.java:1452)
        //       at org.hibernate.query.internal.AbstractProducedQuery.doList(AbstractProducedQuery.java:1649)
        //       at org.hibernate.query.internal.AbstractProducedQuery.list(AbstractProducedQuery.java:1617)
        //       at org.hibernate.query.Query.getResultList(Query.java:165)
        //       at com.sun.proxy.$Proxy131.getTransactionEntitiesByOriginWalletOrDestinationWallet(null)
        //   org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Violación de una restricción de Integridad Referencial: "FKDUUVOX0PC6EURJOBAKXG63P24: PUBLIC.TRANSACTION FOREIGN KEY(DESTINATION_WALLET_ID) REFERENCES PUBLIC.WALLET(WALLET_ID) (CAST(1 AS BIGINT))"
        //   Referential integrity constraint violation: "FKDUUVOX0PC6EURJOBAKXG63P24: PUBLIC.TRANSACTION FOREIGN KEY(DESTINATION_WALLET_ID) REFERENCES PUBLIC.WALLET(WALLET_ID) (CAST(1 AS BIGINT))"; SQL statement:
        //   insert into transaction (amount, destination_wallet_id, origin_wallet_id, transaction_date_time, transaction_type, transaction_id) values (?, ?, ?, ?, ?, ?) [23506-214]
        //       at org.h2.message.DbException.getJdbcSQLException(DbException.java:508)
        //       at org.h2.message.DbException.getJdbcSQLException(DbException.java:477)
        //       at org.h2.message.DbException.get(DbException.java:223)
        //       at org.h2.message.DbException.get(DbException.java:199)
        //       at org.h2.constraint.ConstraintReferential.checkRowOwnTable(ConstraintReferential.java:311)
        //       at org.h2.constraint.ConstraintReferential.checkRow(ConstraintReferential.java:252)
        //       at org.h2.table.Table.fireConstraints(Table.java:1172)
        //       at org.h2.table.Table.fireAfterRow(Table.java:1190)
        //       at org.h2.command.dml.Insert.insertRows(Insert.java:188)
        //       at org.h2.command.dml.Insert.update(Insert.java:135)
        //       at org.h2.command.dml.DataChangeStatement.update(DataChangeStatement.java:74)
        //       at org.h2.command.CommandContainer.update(CommandContainer.java:169)
        //       at org.h2.command.Command.executeUpdate(Command.java:252)
        //       at org.h2.jdbc.JdbcPreparedStatement.executeUpdateInternal(JdbcPreparedStatement.java:209)
        //       at org.h2.jdbc.JdbcPreparedStatement.executeUpdate(JdbcPreparedStatement.java:169)
        //       at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:197)
        //       at org.hibernate.engine.jdbc.batch.internal.NonBatchingBatch.addToBatch(NonBatchingBatch.java:46)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3375)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3937)
        //       at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:107)
        //       at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:604)
        //       at org.hibernate.engine.spi.ActionQueue.lambda$executeActions$1(ActionQueue.java:478)
        //       at java.util.LinkedHashMap.forEach(LinkedHashMap.java:684)
        //       at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:475)
        //       at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:344)
        //       at org.hibernate.event.internal.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:57)
        //       at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
        //       at org.hibernate.internal.SessionImpl.autoFlushIfRequired(SessionImpl.java:1372)
        //       at org.hibernate.internal.SessionImpl.list(SessionImpl.java:1452)
        //       at org.hibernate.query.internal.AbstractProducedQuery.doList(AbstractProducedQuery.java:1649)
        //       at org.hibernate.query.internal.AbstractProducedQuery.list(AbstractProducedQuery.java:1617)
        //       at org.hibernate.query.Query.getResultList(Query.java:165)
        //       at com.sun.proxy.$Proxy131.getTransactionEntitiesByOriginWalletOrDestinationWallet(null)
        //   See https://diff.blue/R013 to resolve this issue.

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());

        WalletEntity destinationWallet = new WalletEntity();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setUser(userEntity);
        destinationWallet.setWalletId(1L);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setName("Name");
        userEntity2.setPhoneNumber("6625550144");
        userEntity2.setUserId(1L);
        userEntity2.setWallet(new HashSet<>());

        WalletEntity originWallet = new WalletEntity();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setUser(userEntity2);
        originWallet.setWalletId(1L);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(1L));
        transactionEntity.setDestinationWallet(destinationWallet);
        transactionEntity.setOriginWallet(originWallet);
        transactionEntity.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity.setTransactionType("Transaction Type");

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("john.smith@example.org");
        userEntity3.setName("42");
        userEntity3.setPhoneNumber("8605550118");
        userEntity3.setUserId(2L);
        userEntity3.setWallet(new HashSet<>());

        WalletEntity destinationWallet2 = new WalletEntity();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setUser(userEntity3);
        destinationWallet2.setWalletId(2L);

        UserEntity userEntity4 = new UserEntity();
        userEntity4.setEmail("john.smith@example.org");
        userEntity4.setName("42");
        userEntity4.setPhoneNumber("8605550118");
        userEntity4.setUserId(2L);
        userEntity4.setWallet(new HashSet<>());

        WalletEntity originWallet2 = new WalletEntity();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setUser(userEntity4);
        originWallet2.setWalletId(2L);

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setAmount(BigDecimal.valueOf(1L));
        transactionEntity2.setDestinationWallet(destinationWallet2);
        transactionEntity2.setOriginWallet(originWallet2);
        transactionEntity2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionEntity2.setTransactionType("42");
        transactionRepository.save(transactionEntity);
        transactionRepository.save(transactionEntity2);
        transactionRepository.getTransactionEntitiesByOriginWalletOrDestinationWallet(1L);
    }
}

