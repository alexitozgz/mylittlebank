package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.domain.service.BlockchainService;

//@RestController
//@RequestMapping("/blockchain")
public class BlockchainController {
    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

}
