/*
 * @ {#} GrantAccess.java   1.0     10/3/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.GrantAccess;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/3/2024
 * @version:    1.0
 */
public interface GrantAccessRepository {
    public List<GrantAccess> findAccountByRoleId(String roleId);

    public List<GrantAccess> findRoleByAccountId(String accountId);

    public boolean save(GrantAccess grantAccess);

    public boolean delete(GrantAccess grantAccess);

    public boolean exists(String accountId, String roleId);
}
