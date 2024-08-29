package com.Springboot.SpringPro.service;

import com.Springboot.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDto);
    AccountDTO geAccountDTO(Long id);

}
