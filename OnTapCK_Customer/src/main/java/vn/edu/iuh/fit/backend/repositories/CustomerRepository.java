/*
 * @ {#} CustomerRepository.java   1.0     20/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.entities.Customer;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   20/12/2024
 * @version:    1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE YEAR(c.custDob) = 2000")
    public List<Customer> findCustomerByYearDob();
}
