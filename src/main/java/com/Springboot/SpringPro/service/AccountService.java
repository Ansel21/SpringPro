package com.Springboot.SpringPro.service;

import java.util.List;

import com.Springboot.SpringPro.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDto);
    AccountDTO geAccountDTO(Long id);
    AccountDTO depositAmount(Long id,Double amount);
    AccountDTO withdrawAccount(Long id,double amount);
    void delAccountDTO(Long id);
    List<AccountDTO> getAllAccount();
}
