/*
 * @ {#} AccountModel.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.frontend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Account;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Customer;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@Component
public class AccountModel {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/";

    public List<Account> getAccountsBetweenBalance(double min, double max) {
        return restTemplate.getForObject(URL + "accounts/between-balance?min=" + min + "&max=" + max, List.class);
    }

    public Customer getAccountById(Long id) {
        return restTemplate.getForObject(URL + "customers/" + id, Customer.class);
    }

    public List<Customer> getCustomersByYear() {
        return restTemplate.getForObject(URL + "customers/by-year-dob", List.class);
    }

    public List<Customer> getCustomers() {
        return restTemplate.getForObject(URL + "customers", List.class);
    }
}

