package com.gevernova.banking_application.mapper;

import com.gevernova.banking_application.dto.AccountDTO;
import com.gevernova.banking_application.entity.Account;

public class AccountMapper {
    public static Account dtoToAccount(AccountDTO dto){
        Account account = new Account(
                dto.getAccount_Number(),
                dto.getAccountHolderName(),
                dto.getBalance()
        );
        return account;
    }
    public static AccountDTO accountToDTO(Account account){
        AccountDTO dto = new AccountDTO(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return dto;
    }
}
