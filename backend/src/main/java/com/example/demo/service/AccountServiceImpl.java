package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.account.UpdateAccountRequest;
import com.example.demo.repository.AccountRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getListAccount() {
        List<Account> accounts = accountRepository.findAll();
//        convert to accountDto
        List<AccountDTO> accountDtos = new ArrayList<AccountDTO>();
        for (Account acc : accounts) {
//            accountDtos.add(AccountMapper.toAccountDto(acc));
        }
        return accountDtos;
    }

    @Override
    public AccountDTO getAccountById(Long id) {
//        Optional<Account> account = accountRepository.findById(id);
//        if (!account.isPresent()) {
//            throw new NotFoundException("Not found account");
//        }
//        return AccountMapper.toAccountDto(account.get());
        return  null;
    }

    @Override
    public AccountDTO createAcc(CreateAccountRequest accountReq) {
////        kiem tra email da ton tai chua
//        Account accountExist = accountRepository.findByEmail(accountReq.getEmail());
//        if (accountExist != null) {
//            throw new InternalException("Email is already in db");
//        }
//
////        convert account req -> account
//        Account account = new Account();
//        account = AccountMapper.createReqToAccount(accountReq);
////        save account vao cssl
//        accountRepository.save(account);
//        return AccountMapper.toAccountDto(account);
        return null;
    }

    @Override
    public AccountDTO updateAcc(UpdateAccountRequest request) {
////        kiem tra email da ton tai chua
//
////        find account in Db
//        Optional<Account> accountRs = accountRepository.findById(request.getId());
//
//        Account account = accountRs.get();
//
////        update info account
//        account.setEmail(request.getEmail());
//        account.setUserName(request.getUserName());
//        account.setPhone(request.getPhone());
//        account.setRole(request.getRole());
//        account.setIsActivate(request.getIsActivate());
////        account.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12)));
//
//        try {
//            accountRepository.save(account);
//        } catch (Exception ex) {
////            throw new InternalServerException("DB error. Can't update account);
//        }

//        return AccountMapper.toAccountDto(account);
        return null;
    }

    @Override
    public void deleteAcc(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> getAccountActivate() {
//        List<Account> accounts = accountRepository.getAccountActivate();
////        convert to accountDto
//        List<AccountDTO> accountDtos = new ArrayList<AccountDTO>();
//        for (Account acc : accounts) {
//            accountDtos.add(AccountMapper.toAccountDto(acc));
//        }
        return null;
    }

    @Override
    public List<AccountDTO> getAccountLock() {
//        List<Account> accounts = accountRepository.getAccountLock();
////        convert Entity to Dto
//        List<AccountDTO> accountDtos = new ArrayList<AccountDTO>();
//        for (Account entity : accounts) {
//            accountDtos.add(AccountMapper.toAccountDto(entity));
//        }
        return null;
    }
}
