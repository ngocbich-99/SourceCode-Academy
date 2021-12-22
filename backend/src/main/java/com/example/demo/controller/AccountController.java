package com.example.demo.controller;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.account.UpdateAccountRequest;
import com.example.demo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Get list account", response = AccountDTO.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getListAccount() {
        List<AccountDTO> accountDtos = accountService.getListAccount();
        return ResponseEntity.ok(accountDtos);
    }

    @ApiOperation(value = "Get account info by id", response = AccountDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @ApiOperation(value = "Create account", response = AccountDTO.class)
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody CreateAccountRequest accountReq) {
        AccountDTO accountDto = accountService.createAcc(accountReq);
        return ResponseEntity.ok(accountDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody UpdateAccountRequest body, @PathVariable int id) {
        return ResponseEntity.ok(accountService.updateAcc(body));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAcc(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("/account-activate")
    public ResponseEntity<?> getAccActivate() {
        List<AccountDTO> listAccDto = accountService.getAccountActivate();
        return ResponseEntity.ok(listAccDto);
    }

    @GetMapping("/lock")
    public ResponseEntity<?> getAccLock() {
        List<AccountDTO> listAcc = accountService.getAccountLock();
        return ResponseEntity.ok(listAcc);
    }

}
