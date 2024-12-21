/*
 * @ {#} AccountService.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.entities.Account;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
public interface AccountService {
    List<Account> getAccountsBetweenBalance(double min, double max);
}
