package com.example.demo.service;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.account.UpdateAccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<AccountDTO> getListAccount();

    public AccountDTO getAccountById(Long id);

    public AccountDTO createAcc(CreateAccountRequest accountReq);

    public AccountDTO updateAcc(UpdateAccountRequest request);

    public void deleteAcc(Long id);

    public List<AccountDTO> getAccountActivate();

    public List<AccountDTO> getAccountLock();
}
