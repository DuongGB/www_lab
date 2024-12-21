/*
 * @ {#} UserController.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.dtos.PageDTO;
import vn.edu.iuh.fit.backend.entities.Customer;
import vn.edu.iuh.fit.fontend.models.CustomerModel;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerModel customerModel;

    @GetMapping("/dashboard")
    public String getAllCustomer(Model model, @RequestParam(defaultValue = "0", required = false) Integer pageNo,
                                 @RequestParam(defaultValue = "2", required = false) Integer pageSize) {
        if (pageNo == null) {
            pageNo = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }
        PageDTO<Customer> customerPageDTO = customerModel.getAll_Paging(pageNo, pageSize);
        model.addAttribute("customers", customerPageDTO);
        return "user/dashboard";
    }

    @GetMapping("/customers")
    public String getAllCustomer(Model model) {
        model.addAttribute("customers", customerModel.getCustomers());
        return "user/customers";
    }

    @GetMapping("/customer/{id}")
    public String getCustomer(Model model, @PathVariable long id) {
        model.addAttribute("customer", customerModel.getAccountById(id));
        return "user/customers-details";
    }

    @GetMapping("/showReport1")
    public String showBlankReport(Model model) {
        return "user/accounts";
    }

    @GetMapping("/report2/customers/by_year_dob")
    public String getCustomersByYear(Model model) {
        model.addAttribute("customers", customerModel.getCustomersByYear());
        return "user/customers";
    }

    @GetMapping("/accounts/between_balance")
    public String getAccountsBetweenBalance(Model model, @RequestParam("min") double min, @RequestParam("max") double max) {
        model.addAttribute("accounts", customerModel.getAccountsBetweenBalance(min, max));
        return "user/accounts";
    }
}

