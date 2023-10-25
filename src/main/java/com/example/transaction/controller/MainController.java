package com.example.transaction.controller;

import com.example.transaction.dto.WithdrawRequest;
import com.example.transaction.dto.WithdrawResponse;
import com.example.transaction.entity.Account;
import com.example.transaction.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private MainService mainService;
    @PostMapping("")
    public Account saveAccount(@RequestBody Account account){
        return mainService.saveAccount(account);
    }
    @PostMapping("/withdraw")
    public WithdrawResponse withdrawMoney(@RequestBody WithdrawRequest withdrawRequest){
        return mainService.withDrawMoney(withdrawRequest);
    }
}
