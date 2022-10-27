package com.test.banking.controller;

import com.test.banking.entity.BankModel;
import com.test.banking.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankingService bankingService;

    //Post Method /api/CreateBankAccount
    @PostMapping("/CreateBankAccount")
    public String createBankAccount(@RequestBody BankModel bankModel) throws ExecutionException, InterruptedException {
        return bankingService.createBankAccount(bankModel);
    }
    //Get Method /api/GetAccountAmount
    @GetMapping("/GetAccountAmount")
    public BankModel getAccountAmount(@RequestParam String currency) throws ExecutionException, InterruptedException {
        return bankingService.getAccountAmount(currency);
    }

    //Put Method /api/DepositMoney
    @PutMapping("/DepositMoney")
    public String depositMoney(@RequestBody BankModel bankModel) throws ExecutionException, InterruptedException {
        return bankingService.updateAccountAmount(bankModel);
    }

    //Delete Method /api/Delete Account
    @DeleteMapping("/DeleteAccount")
    public String deleteAccount(@RequestParam String currency) throws ExecutionException, InterruptedException {
        return bankingService.deleteBankAccount(currency);
    }


}