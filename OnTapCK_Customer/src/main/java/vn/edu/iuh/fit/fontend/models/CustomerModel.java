/*
 * @ {#} CustomerModel.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.fontend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.PageDTO;
import vn.edu.iuh.fit.backend.entities.Account;
import vn.edu.iuh.fit.backend.entities.Customer;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@Component
public class CustomerModel {
    @Autowired
    private RestTemplate restTemplate;

    public List<Customer> getAll() {
        String url = "http://localhost:8080/api/customer/list";
        return restTemplate.getForObject(url, List.class);
    }

    public PageDTO<Customer> getAll_Paging(int pageNo, int pageSize) {
        return restTemplate.getForObject("http://localhost:8080/api/customer/paging?pageNo=" + pageNo + "&pageSize=" + pageSize, PageDTO.class);
    }

    public Customer save(Customer customer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<Customer> request = new HttpEntity<>(customer, httpHeaders);
        ResponseEntity<Customer> response = restTemplate.exchange(
                "http://localhost:8080/api/customer",
                HttpMethod.POST,
                request,
                Customer.class
        );
        return response.getBody();
    }

    public Customer update(Customer customer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<Customer> request = new HttpEntity<>(customer, httpHeaders);
        ResponseEntity<Customer> response = restTemplate.exchange(
                "http://localhost:8080/api/customer",
                HttpMethod.PUT,
                request,
                Customer.class
        );
        return response.getBody();
    }

    public void delete(long id) {
        String url = "http://localhost:8080/api/customer/" + id;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public Customer findById(long id) {
        return restTemplate.getForObject("http://localhost:8080/api/customer/" + id, Customer.class);
    }

    public List<Account> getAccountsBetweenBalance(double min, double max) {
        return restTemplate.getForObject("http://localhost:8080/api/user/accounts/between_balance?min="
                + min +
                "&max=" + max, List.class);
    }

    public Customer getAccountById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/api/user/customers/" + id, Customer.class);
    }

    public List<Customer> getCustomersByYear() {
        return restTemplate.getForObject("http://localhost:8080/api/user/customers/by_year_dob", List.class);
    }

    public List<Customer> getCustomers() {
        return restTemplate.getForObject("http://localhost:8080/api/user/customers", List.class);
    }
}
