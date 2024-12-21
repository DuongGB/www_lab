/*
 * @ {#} CustomerServiceImpl.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dtos.PageDTO;
import vn.edu.iuh.fit.backend.entities.Customer;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.backend.services.CustomerService;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public PageDTO<Customer> getAll_paging(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Customer> customers = customerRepository.findAll(pageable);
        PageDTO<Customer> customerPageDTO = new PageDTO<>();
        customerPageDTO.setTotalPages(customers.getTotalPages());
        customerPageDTO.setTotal(customers.getNumberOfElements());
        customerPageDTO.setSize(customers.getSize());
        customerPageDTO.setPage(customers.getNumber());
        customerPageDTO.setValues(customers.getContent());
        return customerPageDTO;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer existsCustomer = customerRepository.findById(customer.getId()).orElse(null);
        System.out.println("Customer" + existsCustomer.getId());
        if (existsCustomer == null) {
            return null;
        }
        existsCustomer.setCustName(customer.getCustName());
        existsCustomer.setCustDob(customer.getCustDob());
        existsCustomer.setCustEmail(customer.getCustEmail());
        existsCustomer.setCustAddress(customer.getCustAddress());
        return customerRepository.save(existsCustomer);
    }

    @Override
    public Customer findById(long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        if (!customerRepository.existsById(id)) {
            return;
        }
        customerRepository.deleteById(id);
    }

    @Override
    public boolean existsBYId(long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public List<Customer> getCustomersByYearDob() {
        return customerRepository.findCustomerByYearDob();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }
}
