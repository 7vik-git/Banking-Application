package com.gevernova.banking_application.service;

import com.gevernova.banking_application.dto.AccountDTO;


public interface AccountService {
    AccountDTO createAccount(AccountDTO dto);
    AccountDTO getAccountDetails(Long id);
    void deleteAccount(Long id);
    AccountDTO depositMoney(Long id, double ammount);
    AccountDTO withdrawMoney(Long id, double money);

}
