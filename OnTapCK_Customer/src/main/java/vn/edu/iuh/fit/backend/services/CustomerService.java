/*
 * @ {#} CustomerService.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.backend.dtos.PageDTO;
import vn.edu.iuh.fit.backend.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    PageDTO<Customer> getAll_paging(int pageNo, int pageSize);

    Customer save(Customer customer);

    Customer update(Customer customer);

    Customer findById(long id);

    void delete(long id);

    boolean existsBYId(long id);

    public List<Customer> getCustomersByYearDob();

    public Customer getCustomerById(Long id);
}
