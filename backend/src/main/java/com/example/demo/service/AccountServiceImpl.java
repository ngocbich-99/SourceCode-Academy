package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.mapper.AccountMapper;
import com.example.demo.model.request.CreateAccountReq;
import com.example.demo.model.request.UpdateAccountReq;
import com.example.demo.repository.AccountRepository;
import org.mindrot.jbcrypt.BCrypt;
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
    public List<AccountDto> getListAccount() {
        List<Account> accounts = accountRepository.findAll();
//        convert to accountDto
        List<AccountDto> accountDtos = new ArrayList<AccountDto>();
        for (Account acc : accounts) {
            accountDtos.add(AccountMapper.toAccountDto(acc));
        }
        return accountDtos;
    }

    @Override
    public AccountDto getAccountById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new NotFoundException("Not found account");
        }
        return AccountMapper.toAccountDto(account.get());
    }

    @Override
    public AccountDto createAcc(CreateAccountReq accountReq) {
//        kiem tra email da ton tai chua

//        convert account req -> account
        Account account = new Account();
        account = AccountMapper.createReqToAccount(accountReq);
//        save account vao cssl
        accountRepository.save(account);
        return AccountMapper.toAccountDto(account);
    }

    @Override
    public AccountDto updateAcc(UpdateAccountReq accountReq, int id) {
//        kiem tra email da ton tai chua
        Optional<Account> accountRs = accountRepository.findById(id);

//        convert accountReq -> Account
        Account account = accountRs.get();

//        update info account
        account.setEmail(accountReq.getEmail());
        account.setUserName(accountReq.getUserName());
        account.setPhone(accountReq.getPhone());
        account.setRole(accountReq.getRole());
        account.setIsActivate(accountReq.getIsActivate());
//        account.setPassword(BCrypt.hashpw(accountReq.getPassword(), BCrypt.gensalt(12)));

        try {
            accountRepository.save(account);
        } catch (Exception ex) {
//            throw new InternalServerException("DB error. Can't update account);
        }

        return AccountMapper.toAccountDto(account);
    }

    @Override
    public void deleteAcc(int id) {
        Optional<Account> account = accountRepository.findById(id);

        accountRepository.deleteById(id);
    }
}
