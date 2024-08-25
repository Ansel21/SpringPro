package com.Springboot.SpringPro.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Springboot.SpringPro.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
    
}
