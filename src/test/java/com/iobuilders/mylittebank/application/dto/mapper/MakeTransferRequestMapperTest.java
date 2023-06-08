package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.request.MakeTransferRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {MakeTransferRequestMapper.class})
@ExtendWith(SpringExtension.class)
class MakeTransferRequestMapperTest {
    @Autowired
    private MakeTransferRequestMapper makeTransferRequestMapper;

    private MakeTransferRequest makeTransferRequest;
    @BeforeEach
    void init() {
        makeTransferRequest = new MakeTransferRequest();
        BigDecimal amount = BigDecimal.valueOf(1L);
        makeTransferRequest.setAmount(amount);
        makeTransferRequest.setDestinationWalletId(1L);
        makeTransferRequest.setOriginWalletId(1L);
    }

    @Test
    void fromMakeTransferRequestToTransaction_result_ok() {
        Transaction transactionResult = makeTransferRequestMapper.toTransaction(makeTransferRequest);

        assertEquals(makeTransferRequest.getOriginWalletId(), transactionResult.getOriginWallet().getWalletId());
        assertEquals(makeTransferRequest.getDestinationWalletId(), transactionResult.getDestinationWallet().getWalletId());
        assertEquals(makeTransferRequest.getAmount(), transactionResult.getAmount());
    }

    @Test
    void fromMakeTransferRequestToTransaction_verify_maketransferrequests_calls() {
        makeTransferRequest = mock(MakeTransferRequest.class);

        makeTransferRequestMapper.toTransaction(makeTransferRequest);

        verify(makeTransferRequest).getOriginWalletId();
        verify(makeTransferRequest).getDestinationWalletId();
        verify(makeTransferRequest).getAmount();
    }

    @Test
    void fromMakeTransferRequestToTransaction_result_ko() {
        assertThrows(NullPointerException.class, () -> makeTransferRequestMapper.toTransaction(null));
    }

}

