/*
 * @ {#} AccountRepository.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.entities.Account;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.accNumber >= ?1 AND a.accNumber <= ?2")
    List<Account> getAccountsBetweenBalance(double min, double max);
}
