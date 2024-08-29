package com.Springboot.SpringPro.mapper;

import org.slf4j.LoggerFactory;

import com.Springboot.SpringPro.entity.Account;
import com.Springboot.dto.AccountDTO;
import org.slf4j.Logger;
public class AccountMapper {
    static private final Logger logger = LoggerFactory.getLogger(AccountMapper.class);

    public static Account mapToAccount(AccountDTO accountDTO){
        Account account = new Account(accountDTO.getId(),accountDTO.getAccountHolderName(),accountDTO.getBalance());
        return account;
    }

    public static AccountDTO mapAccountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO(account.getId(),account.getAccountHolderName(),account.getBalance());
        logger.info("AccountDTOVal:{}",accountDTO);
        return accountDTO;
    }
}
