/*
 * @ {#} AccountImpl.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Account;
import vn.edu.iuh.fit.jpasecurity_customer.backend.repositories.AccountRepository;
import vn.edu.iuh.fit.jpasecurity_customer.backend.services.AccountService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@Service
public class AccountImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccountsBetweenBalance(double min, double max) {
        return accountRepository.getAccountsBetweenBalance(min, max);
    }
}

