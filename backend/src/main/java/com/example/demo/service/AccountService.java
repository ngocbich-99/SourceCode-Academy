package com.example.demo.service;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.ChangePasswordRequest;
import com.example.demo.model.request.account.ChangeUserInfoRequest;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.account.UpdateAccountRequest;
import com.example.demo.model.request.auth.LoginRequest;
import com.example.demo.model.request.auth.RegisterAccountRequest;
import com.example.demo.model.response.user.LoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> getListAccount();

    public AccountDTO getAccountById(Long id);

    public AccountDTO createAcc(CreateAccountRequest accountReq);

    public AccountDTO updateAcc(UpdateAccountRequest request);

    public void deleteAcc(Long id);

    public List<AccountDTO> getAccountActivate();

    public List<AccountDTO> getAccountLock();

    public AccountDTO reg(RegisterAccountRequest request);

    public LoginResponse login(LoginRequest request) throws JsonProcessingException;

    public AccountDTO getCurrentUser();

    public void changePassword(ChangePasswordRequest request);

    public AccountDTO changeUserInfo(ChangeUserInfoRequest request);
}
