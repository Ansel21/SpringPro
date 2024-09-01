package com.Springboot.SpringPro.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import com.Springboot.SpringPro.service.AccountService;
import com.Springboot.dto.AccountDTO;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService= accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(accountDTO),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Long id){
        logger.info("id value :{}",id);
        AccountDTO accounts = accountService.geAccountDTO(id);
        return ResponseEntity.ok(accounts);
    }
    
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable("id") Long id, @RequestBody Map<String,Double> request ){

        Double amount = request.get("amount");
        logger.info("value is : {}",amount);
        AccountDTO account = accountService.depositAmount(id, amount);
        return ResponseEntity.ok(account);

    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable("id") Long id, @RequestBody Map<String,Double> request ){

        Double amount = request.get("amount");
        logger.info("value is : {}",amount);
        AccountDTO account = accountService.withdrawAccount(id, amount);
        return ResponseEntity.ok(account);

    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id){
        accountService.delAccountDTO(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }
}
