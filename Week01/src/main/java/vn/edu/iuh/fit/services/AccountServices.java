/*
 * @ {#} AccountServices.java   1.0     10/3/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.GrantAccessId;
import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.Impl.AccountRepositoryImpl;
import vn.edu.iuh.fit.repositories.Impl.GrantAccessRepositoryImpl;
import vn.edu.iuh.fit.repositories.Impl.RoleRepositoryImpl;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/3/2024
 * @version:    1.0
 */
public class AccountServices {
    private AccountRepository accountRepository;
    private GrantAccessRepository grantAccessRepository;
    private RoleRepository roleRepository;

    public AccountServices() {
        this.accountRepository = new AccountRepositoryImpl();
        this.grantAccessRepository = new GrantAccessRepositoryImpl();
        this.roleRepository = new RoleRepositoryImpl();
    }

    public boolean addAccount(Account account) {
        if (check(account)) {
            return false;
        } else {
            accountRepository.save(account);

            Role userRole = roleRepository.findById("user");

            GrantAccess grantAccess = new GrantAccess();
            GrantAccessId grantAccessId = new GrantAccessId();
            grantAccessId.setAccountId(account.getAccountId());
            grantAccessId.setRoleId(userRole.getRoleId());

            grantAccess.setId(grantAccessId);
            grantAccess.setAccount(account);
            grantAccess.setRole(userRole);

            return grantAccessRepository.save(grantAccess);
        }
    }

    private boolean check(Account account) {
        AccountRepository accountRepository = new AccountRepositoryImpl();
        return accountRepository.exists(account.getAccountId());
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findAccountById(String id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAccountByRoleName(String roleName) {
        return accountRepository.findAccountByRoleName(roleName);
    }

    public List<Account> findAccountByRoleId(String roleId) {
        return accountRepository.findAccountByRoleId(roleId);
    }

    public Account updateAccount(Account account) {
        return accountRepository.update(account);
    }

    public boolean deleteAccount(Account account) {
        return accountRepository.delete(account);
    }
}

