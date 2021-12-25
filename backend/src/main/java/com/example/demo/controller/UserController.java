package com.example.demo.controller;

import com.example.demo.constant.CommonConstant;
import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.account.ChangePasswordRequest;
import com.example.demo.model.request.account.ChangeUserInfoRequest;
import com.example.demo.model.request.section.UpdateSectionRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AccountService accountService;

    @PostMapping("/change-password")
    public CloudResponse<String> changePassword(@Valid @RequestBody ChangePasswordRequest body){
        accountService.changePassword(body);
        return CloudResponse.ok(CommonConstant.SUCCESS,"Change password success");
    }
    @PostMapping("/change-user-info")
    public CloudResponse<AccountDTO> changeUserInfo(@Valid @RequestBody ChangeUserInfoRequest body){
        return CloudResponse.ok(accountService.changeUserInfo(body));
    }

    @GetMapping
    public CloudResponse<AccountDTO> getCurrentUser(){
        return CloudResponse.ok(accountService.getCurrentUser());
    }

}
