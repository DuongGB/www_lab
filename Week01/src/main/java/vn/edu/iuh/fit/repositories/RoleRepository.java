/*
 * @ {#} RoleRepository.java   1.0     10/3/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Role;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/3/2024
 * @version:    1.0
 */
public interface RoleRepository {
    public List<Role> findAll();

    public Role findById(String id);

    public boolean exists(String id);

    public void save(Role role);

    public Role update(Role role);

    public boolean delete(Role role);

    public boolean isAdministrator(String accountId);
}
