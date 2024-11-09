/*
 * @ {#} AccountService.java   1.0     10/19/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/19/2024
 * @version:    1.0
 */

public interface AccountService {
    List<Account> pageNative(int page, int size);

    List<Account> findByBalanceGreaterThan(int page, int size, double amount);
}

