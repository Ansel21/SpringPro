package com.Springboot.SpringPro.service.Impl;

import org.springframework.stereotype.Service;

import com.Springboot.SpringPro.entity.Account;
import com.Springboot.SpringPro.mapper.AccountMapper;
import com.Springboot.SpringPro.repository.AccountRepository;
import com.Springboot.SpringPro.service.AccountService;
import com.Springboot.dto.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        // Log the savedAccount details
        logger.info("Ansel Account: {}", accountDto);

        return AccountMapper.mapAccountDTO(savedAccount);
    }
}
