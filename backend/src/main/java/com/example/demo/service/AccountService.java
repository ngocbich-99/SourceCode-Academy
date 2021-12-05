package com.example.demo.service;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.request.CreateAccountReq;
import com.example.demo.model.request.UpdateAccountReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<AccountDto> getListAccount();

    public AccountDto getAccountById(int id);

    public AccountDto createAcc(CreateAccountReq accountReq);

    public AccountDto updateAcc(UpdateAccountReq accountReq, int id);

    public void deleteAcc(int id);

    public List<AccountDto> getAccountActivate();
}
