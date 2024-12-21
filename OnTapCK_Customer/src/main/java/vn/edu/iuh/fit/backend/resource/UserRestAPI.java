/*
 * @ {#} UserRestAPI.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.entities.Account;
import vn.edu.iuh.fit.backend.entities.Customer;
import vn.edu.iuh.fit.backend.services.AccountService;
import vn.edu.iuh.fit.backend.services.CustomerService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserRestAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/by_year_dob")
    public ResponseEntity<List<Customer>> getCustomersByYear() {
        return ResponseEntity.ok(customerService.getCustomersByYearDob());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping("/accounts/between_balance")
    public ResponseEntity<List<Account>> getAccountsBetweenBalance(@RequestParam double min,@RequestParam double max) {
        return ResponseEntity.ok(accountService.getAccountsBetweenBalance(min, max));
    }
}

