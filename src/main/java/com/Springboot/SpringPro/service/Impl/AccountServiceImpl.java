package com.Springboot.SpringPro.service.Impl;

import org.springframework.stereotype.Service;

import com.Springboot.SpringPro.dto.AccountDTO;
import com.Springboot.SpringPro.entity.Account;
import com.Springboot.SpringPro.mapper.AccountMapper;
import com.Springboot.SpringPro.repository.AccountRepository;
import com.Springboot.SpringPro.service.AccountService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        logger.info("Ansel Account: {}",account);

        return AccountMapper.mapAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO geAccountDTO(Long id) {
        Account value = accountRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Account not found"));

        return AccountMapper.mapAccountDTO((value));
    }

    @Override
    public AccountDTO depositAmount(Long id,Double amount){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        Double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account saveAccount = accountRepository.save(account);
        logger.info("Account info : {}",amount);
        return AccountMapper.mapAccountDTO(saveAccount);
    }

    @Override
    public AccountDTO withdrawAccount(Long id,double amount){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        logger.info("ansel evander:{}",amount);
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        double totalAmount = account.getBalance() - amount;
        account.setBalance(totalAmount);
        Account saveAccount = accountRepository.save(account);
        logger.info("Account details:{}",saveAccount);
        return AccountMapper.mapAccountDTO(saveAccount);
    }

    @Override 
    public List<AccountDTO> getAllAccount(){
        List<Account> accounts = accountRepository.findAll();
        logger.info("Accounts information:{}",accounts.stream().map(account -> AccountMapper.mapAccountDTO(account)).collect(Collectors.toList()));
        return accounts.stream().map(account -> AccountMapper.mapAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public void delAccountDTO(Long id){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }
}
