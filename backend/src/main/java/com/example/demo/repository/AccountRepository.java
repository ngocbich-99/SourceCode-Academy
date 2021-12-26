package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByEmail(String email);

    @Query(value = "Select * from account where is_activate = true", nativeQuery = true)
    List<Account> getAccountActivate();

    @Query(value = "Select * from account where is_activate = false ", nativeQuery = true)
    List<Account> getAccountLock();

    Account findAccountByEmailOrUsernameOrPhone(String email,String username, String phone);
}
