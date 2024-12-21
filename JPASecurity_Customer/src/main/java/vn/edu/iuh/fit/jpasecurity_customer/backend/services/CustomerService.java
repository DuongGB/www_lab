/*
 * @ {#} CustomerService.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.backend.services;

import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Customer;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
public interface CustomerService {
    public List<Customer> getAllCustomers();

    public List<Customer> getCustomerByYearDob();

    public Customer getCustomerById(Long id);
}
