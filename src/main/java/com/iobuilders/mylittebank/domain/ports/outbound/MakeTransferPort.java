package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.model.Transaction;

public interface MakeTransferPort {
    Long createTransfer(Transaction transaction);
}
