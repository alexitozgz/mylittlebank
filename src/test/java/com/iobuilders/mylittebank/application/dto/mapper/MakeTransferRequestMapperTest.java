package com.iobuilders.mylittebank.application.dto.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.application.dto.request.MakeTransferRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MakeTransferRequestMapper.class})
@ExtendWith(SpringExtension.class)
class MakeTransferRequestMapperTest {
    @Autowired
    private MakeTransferRequestMapper makeTransferRequestMapper;

    /**
     * Method under test: {@link MakeTransferRequestMapper#toTransaction(MakeTransferRequest)}
     */
    @Test
    void testToTransaction() {
        MakeTransferRequest makeTransferRequest = new MakeTransferRequest();
        BigDecimal amount = BigDecimal.valueOf(1L);
        makeTransferRequest.setAmount(amount);
        makeTransferRequest.setDestinationWalletId(1L);
        makeTransferRequest.setOriginWalletId(1L);
        Transaction actualToTransactionResult = makeTransferRequestMapper.toTransaction(makeTransferRequest);
        BigDecimal expectedAmount = amount.ONE;
        BigDecimal amount2 = actualToTransactionResult.getAmount();
        assertSame(expectedAmount, amount2);
        assertEquals(1L, actualToTransactionResult.getOriginWallet().getWalletId());
        assertEquals("1", amount2.toString());
        assertEquals(1L, actualToTransactionResult.getDestinationWallet().getWalletId());
    }

    /**
     * Method under test: {@link MakeTransferRequestMapper#toTransaction(MakeTransferRequest)}
     */
    @Test
    void testToTransaction2() {
        MakeTransferRequest makeTransferRequest = mock(MakeTransferRequest.class);
        when(makeTransferRequest.getAmount()).thenReturn(BigDecimal.valueOf(1L));
        when(makeTransferRequest.getDestinationWalletId()).thenReturn(1L);
        when(makeTransferRequest.getOriginWalletId()).thenReturn(1L);
        doNothing().when(makeTransferRequest).setAmount(Mockito.<BigDecimal>any());
        doNothing().when(makeTransferRequest).setDestinationWalletId(anyLong());
        doNothing().when(makeTransferRequest).setOriginWalletId(anyLong());
        BigDecimal amount = BigDecimal.valueOf(1L);
        makeTransferRequest.setAmount(amount);
        makeTransferRequest.setDestinationWalletId(1L);
        makeTransferRequest.setOriginWalletId(1L);
        Transaction actualToTransactionResult = makeTransferRequestMapper.toTransaction(makeTransferRequest);
        BigDecimal expectedAmount = amount.ONE;
        BigDecimal amount2 = actualToTransactionResult.getAmount();
        assertSame(expectedAmount, amount2);
        assertEquals(1L, actualToTransactionResult.getOriginWallet().getWalletId());
        assertEquals("1", amount2.toString());
        assertEquals(1L, actualToTransactionResult.getDestinationWallet().getWalletId());
        verify(makeTransferRequest).getAmount();
        verify(makeTransferRequest).getDestinationWalletId();
        verify(makeTransferRequest).getOriginWalletId();
        verify(makeTransferRequest).setAmount(Mockito.<BigDecimal>any());
        verify(makeTransferRequest).setDestinationWalletId(anyLong());
        verify(makeTransferRequest).setOriginWalletId(anyLong());
    }
}

