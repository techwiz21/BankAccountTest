package com.test.banking.controller;

import com.test.banking.entity.BankModel;
import com.test.banking.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankingService bankingService;
    @PostMapping("/CreateBankAccount")
    public String saveBankAccount(@RequestBody BankModel bankModel) throws ExecutionException, InterruptedException {
        return bankingService.SaveBankAccount(bankModel);
    }


}