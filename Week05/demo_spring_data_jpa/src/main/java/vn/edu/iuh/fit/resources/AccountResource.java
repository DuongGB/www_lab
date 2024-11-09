/*
 * @ {#} AccountResource.java   1.0     10/19/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.models.entities.Account;
import vn.edu.iuh.fit.services.AccountService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/19/2024
 * @version:    1.0
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountResource {
    private final AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{page}/{size}")
    public List<Account> getAccount(@PathVariable int page, @PathVariable int size) {
        return accountService.pageNative(page, size);

    }

    @GetMapping("/{page}/{size}/{amount}")
    public List<Account> getAccount(@PathVariable int page, @PathVariable int size, @PathVariable double amount) {
        return accountService.findByBalanceGreaterThan(page, size, amount);
    }
}

