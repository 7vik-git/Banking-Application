package com.gevernova.banking_application.service.impl;

import com.gevernova.banking_application.dto.AccountDTO;
import com.gevernova.banking_application.entity.Account;
import com.gevernova.banking_application.exceptions.AccountNotFoundException;
import com.gevernova.banking_application.exceptions.InsufficientBalanceException;
import com.gevernova.banking_application.mapper.AccountMapper;
import com.gevernova.banking_application.repository.AccountRepository;
import com.gevernova.banking_application.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO createAccount(AccountDTO dto) {
       Account account = AccountMapper.dtoToAccount(dto);
       Account savedAccount = accountRepository.save(account);
       log.info("Created Account Successfully");
       return AccountMapper.accountToDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountDetails(Long id) {
        AccountDTO dto = AccountMapper.accountToDTO(accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account with id:"+ id +"  not found")));
        return dto;
    }

    @Override
    public void deleteAccount(Long id) {
        if(!accountRepository.existsById(id)){
            throw new AccountNotFoundException("Account with id:+ id + not found");
        }
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDTO depositMoney(Long id, double money) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance()+money);
            accountRepository.save(account);
            log.info("Money Deposited Successfully");
            return AccountMapper.accountToDTO(account);
        }
         throw new AccountNotFoundException("Account Not Found");
    }

    @Override
    public AccountDTO withdrawMoney(Long id, double money){
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account Not Found"));
        if(account.getBalance() >= money){
            account.setBalance(account.getBalance()-money);
            accountRepository.save(account);
            log.info("Money Withdrawn Successfully");
            return AccountMapper.accountToDTO(account);
        }
        throw new InsufficientBalanceException("Insufficient Balance");
    }
}
