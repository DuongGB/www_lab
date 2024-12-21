/*
 * @ {#} DataGenerate.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Account;
import vn.edu.iuh.fit.jpasecurity_customer.backend.entities.Customer;
import vn.edu.iuh.fit.jpasecurity_customer.backend.enums.AccountStatus;
import vn.edu.iuh.fit.jpasecurity_customer.backend.repositories.AccountRepository;
import vn.edu.iuh.fit.jpasecurity_customer.backend.repositories.CustomerRepository;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@SpringBootTest
public class DataGenerate {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void generateCustomer() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setAddress(faker.address().fullAddress());
            customer.setEmail(faker.internet().emailAddress());
            customer.setDob(faker.timeAndDate().birthday());
            customerRepository.save(customer);
        }
    }

    @Test
    public void generateAccount() {
        Faker faker = new Faker();
        for (int i = 1; i <= 3; i++) {
            Customer customer = customerRepository.findById((long) i).get();
            for (int j = 1; j <= 3; j++) {
                Account account = new Account();
                account.setBalance(faker.number().randomDouble(2, 100, 1000));
                account.setCustomer(customer);
                account.setStatus(AccountStatus.CHECKING);
                accountRepository.save(account);
            }

        }
    }
}

