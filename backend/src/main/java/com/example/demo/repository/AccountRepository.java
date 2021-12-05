package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByEmail(String email);

    @Query(value = "Select * from account where is_activate = 1", nativeQuery = true)
    List<Account> getAccountActivate();
}
