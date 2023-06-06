package com.iobuilders.mylittebank.interfaces.rest;

import com.iobuilders.mylittebank.domain.services.BlockchainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blockchain")
public class BlockchainController {
    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

}
