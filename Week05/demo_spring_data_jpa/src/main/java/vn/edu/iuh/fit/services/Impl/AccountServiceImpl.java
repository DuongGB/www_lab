/*
 * @ {#} AccountServiceImpl.java   1.0     10/19/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.services.AccountService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/19/2024
 * @version:    1.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> pageNative(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return accountRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Account> findByBalanceGreaterThan( int page, int size,double amount) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return accountRepository.findByBalanceGreaterThan(amount, pageRequest);
    }
}

