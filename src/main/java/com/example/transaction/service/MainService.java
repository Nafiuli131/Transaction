package com.example.transaction.service;

import com.example.transaction.dto.WithdrawRequest;
import com.example.transaction.dto.WithdrawResponse;
import com.example.transaction.entity.Account;
import com.example.transaction.entity.WithdrawHistory;
import com.example.transaction.repository.AccountRepository;
import com.example.transaction.repository.WithdrawHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WithdrawHistoryRepository withdrawHistoryRepository;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public WithdrawResponse withDrawMoney(WithdrawRequest withdrawRequest) {
        Account account = new Account();
        account = accountRepository.findById(withdrawRequest.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        WithdrawHistory withdrawHistory = new WithdrawHistory();
        withdrawHistory.setAcName(account.getAcName());
        withdrawHistory.setWithdrawMoney(withdrawRequest.getWithdrawMoney());
        withdrawHistoryRepository.save(withdrawHistory);
        if (withdrawRequest.getWithdrawMoney() > account.getTotalAmount()) {
            throw new RuntimeException("Shortage of money");
        }
        account.setTotalAmount(account.getTotalAmount() - withdrawRequest.getWithdrawMoney());
        accountRepository.save(account);
        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.setAcName(account.getAcName());
        withdrawResponse.setTotalAmount(withdrawRequest.getWithdrawMoney());
        return withdrawResponse;
    }
}
