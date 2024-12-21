/*
 * @ {#} CustomerRepository.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Customer;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE YEAR(c.dob) = 2000")
    public List<Customer> findByCustomerByYearDob();
}
