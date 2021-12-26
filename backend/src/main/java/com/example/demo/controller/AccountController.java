package com.example.demo.controller;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.account.UpdateAccountRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.constant.CommonConstant.SUCCESS;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Get list account", response = CloudResponse.class, responseContainer = "List")
    @GetMapping
    public CloudResponse<List<AccountDTO>> getListAccount() {
        List<AccountDTO> accountDTOS = accountService.getListAccount();
        return CloudResponse.ok(accountDTOS);
    }

    @ApiOperation(value = "Get account info by id", response = AccountDTO.class)
    @GetMapping("/{id}")
    public CloudResponse<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO accountDto = accountService.getAccountById(id);
        return CloudResponse.ok(accountDto);
    }

    @ApiOperation(value = "Create account", response = AccountDTO.class)
    @PostMapping
    public CloudResponse<AccountDTO> createAccount(@Valid @RequestBody CreateAccountRequest accountReq) {
        AccountDTO accountDto = accountService.createAcc(accountReq);
        return CloudResponse.ok(accountDto, "Create account success");
    }


    @ApiOperation(value = "update account")
    @PutMapping("")
    public CloudResponse<AccountDTO> updateAccount(@Valid @RequestBody UpdateAccountRequest body) {
        return CloudResponse.ok(accountService.updateAcc(body), "Update account success");
    }


    @ApiOperation(value = "delete account")
    @DeleteMapping("/{id}")
    public CloudResponse<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAcc(id);
        return CloudResponse.ok(SUCCESS, "Delete account success");
    }

    @ApiOperation(value = "get list active account")
    @GetMapping("/activate")
    public CloudResponse<List<AccountDTO>> getAccActivate() {
        List<AccountDTO> listAccDto = accountService.getAccountActivate();
        return CloudResponse.ok(listAccDto);
    }

    @ApiOperation(value = "get list lock account")
    @GetMapping("/lock")
    public CloudResponse<List<AccountDTO>> getAccLock() {
        List<AccountDTO> listAcc = accountService.getAccountLock();
        return CloudResponse.ok(listAcc);
    }

}
