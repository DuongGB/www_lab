/*
 * @ {#} HomeController.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.jpasecurity_customer.frontend.models.AccountModel;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@Controller
public class HomeController {
    @Autowired
    private AccountModel accountModel;

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", accountModel.getCustomers());
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String getCustomerById(Model model, @PathVariable Long id) {
        model.addAttribute("customer", accountModel.getAccountById(id));
        return "customers-details";
    }

    @GetMapping("/report2/customers/by-year-dob")
    public String getCustomersByYear(Model model) {
        model.addAttribute("customers", accountModel.getCustomersByYear());
        return "customers";
    }

    @GetMapping("/showReport1")
    public String showBlankReport(Model model) {
        return "accounts";
    }

    @GetMapping("/accounts/between-balance")
    public String getAccountsBetweenBalance(Model model, @RequestParam("min") double min, @RequestParam("max") double max) {
        model.addAttribute("accounts", accountModel.getAccountsBetweenBalance(min, max));
        return "accounts";
    }
}

