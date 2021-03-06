package com.example.demo.model.mapper;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.request.CreateAccountReq;
import org.mindrot.jbcrypt.BCrypt;

public class AccountMapper {
    public static AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setIdAccount(account.getIdAccount());
        accountDto.setEmail(account.getEmail());
        accountDto.setPhone(account.getPhone());
        accountDto.setRole(account.getRole());
        accountDto.setUserName(account.getUserName());
        accountDto.setCreatedTime(account.getCreatedTime());
        accountDto.setIsActivate(account.getIsActivate());

        return accountDto;
    }

    public static Account createReqToAccount(CreateAccountReq accountReq) {
        Account account = new Account();
        account.setEmail(accountReq.getEmail());
        account.setPhone(accountReq.getPhone());
        account.setUserName(accountReq.getUserName());
        account.setIsActivate(accountReq.getIsActivate());
        if (accountReq.getRole().equals("")) {
            account.setRole("STUDENT");
        } else {
            account.setRole(accountReq.getRole());
        }
        account.setCreatedTime(accountReq.getCreatedTime());

//        ma hoa password
        account.setPassword(BCrypt.hashpw(accountReq.getPassword(), BCrypt.gensalt(12)));

        return account;
    }

}
