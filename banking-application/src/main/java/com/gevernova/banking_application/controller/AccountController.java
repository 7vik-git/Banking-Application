package com.gevernova.banking_application.controller;

import com.gevernova.banking_application.dto.AccountDTO;
import com.gevernova.banking_application.entity.Account;
import com.gevernova.banking_application.mapper.AccountMapper;
import com.gevernova.banking_application.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/new")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO dto){
        return new ResponseEntity<>(accountService.createAccount(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id){
         AccountDTO dto = accountService.getAccountDetails(id);
         return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdrawMoney(@PathVariable Long id, @RequestBody Map<String,Object>requestBody){
        double money = (double)requestBody.get("money");
        return ResponseEntity.ok(accountService.withdrawMoney(id,money));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> depositMoney(@PathVariable long id, @RequestBody Map<String,Object>requestBody){
        double money = (double) requestBody.get("money");
        AccountDTO dto = accountService.depositMoney(id,money);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully");
    }


}