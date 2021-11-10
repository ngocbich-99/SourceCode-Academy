package com.example.demo.controller;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.request.CreateAccountReq;
import com.example.demo.model.request.UpdateAccountReq;
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
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Get list account", response = AccountDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code=500,message = "")
    })
    @GetMapping("")
    public ResponseEntity<?> getListAccount() {
        List<AccountDto> accountDtos = accountService.getListAccount();
        return ResponseEntity.ok(accountDtos);
    }

    @ApiOperation(value = "Get account info by id", response = AccountDto.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No account found"),
            @ApiResponse(code=500,message = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable int id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @ApiOperation(value = "Create account", response = AccountDto.class)
    @ApiResponses({
            @ApiResponse(code=400,message = "Email already exists in the system"),
            @ApiResponse(code=500,message = "")
    })
    @PostMapping("")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountReq accountReq) {
        AccountDto accountDto = accountService.createAcc(accountReq);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody UpdateAccountReq accountReq, @PathVariable int id) {
        AccountDto accountDto = accountService.updateAcc(accountReq, id);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        accountService.deleteAcc(id);
        return ResponseEntity.ok("Delete success");
    }

}
