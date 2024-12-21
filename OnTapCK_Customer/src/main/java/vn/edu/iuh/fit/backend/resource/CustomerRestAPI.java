/*
 * @ {#} CustomerComtroller.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.dtos.PageDTO;
import vn.edu.iuh.fit.backend.entities.Customer;
import vn.edu.iuh.fit.backend.services.CustomerService;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerRestAPI {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/list")
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers=customerService.getAll();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/paging")
    public ResponseEntity<PageDTO<Customer>> getAll_Paging(int pageNo, int pageSize){
        return ResponseEntity.ok(customerService.getAll_paging(pageNo,pageSize));
    }
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.save(customer));
    }
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.update(customer));
    }
    @RequestMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") long id){
        return ResponseEntity.ok(customerService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        if (!customerService.existsBYId(id)){
            return ResponseEntity.notFound().build();
        }
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
