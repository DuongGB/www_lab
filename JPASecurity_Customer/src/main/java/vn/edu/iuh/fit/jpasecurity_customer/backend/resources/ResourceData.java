/*
 * @ {#} ResourceData.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.backend.resources;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Account;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Customer;
import vn.edu.iuh.fit.jpasecurity_customer.backend.services.AccountService;
import vn.edu.iuh.fit.jpasecurity_customer.backend.services.CustomerService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@RestController
@RequestMapping("/api")
public class ResourceData {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/by-year-dob")
    public ResponseEntity<List<Customer>> getCustomersByYearDob() {
        List<Customer> customers = customerService.getCustomerByYearDob();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/accounts/between-balance")
    public ResponseEntity<List<Account>> getAccountsBetweenBalance(@RequestParam double min,@RequestParam double max) {
        return ResponseEntity.ok(accountService.getAccountsBetweenBalance(min, max));
    }
}

