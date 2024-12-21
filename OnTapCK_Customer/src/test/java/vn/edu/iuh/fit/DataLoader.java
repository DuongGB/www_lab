/*
 * @ {#} DataLoader.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.backend.entities.Account;
import vn.edu.iuh.fit.backend.entities.Customer;
import vn.edu.iuh.fit.backend.repositories.AccountRepository;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@SpringBootTest
public class DataLoader {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void GenerateCustomer() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setCustName(faker.name().fullName());

        }
    }

    @Test
    void GenerateAccount() {

    }
}

