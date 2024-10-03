/*
 * @ {#} AccountRepository.java   1.0     10/3/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/3/2024
 * @version:    1.0
 */
public interface AccountRepository {
    public List<Account> findAll();

    public Account findById(String id);

    public List<Account> findAccountByRoleName(String roleName);

    public List<Account> findAccountByRoleId(String roleName);

    public boolean exists(String id);

    public void save(Account account);

    public Account update(Account account);

    public boolean delete(Account account);
}
