/*
 * @ {#} CustomerController.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.backend.dtos.PageDTO;
import vn.edu.iuh.fit.backend.entities.Customer;
import vn.edu.iuh.fit.backend.resource.CustomerRestAPI;
import vn.edu.iuh.fit.fontend.models.CustomerModel;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    private CustomerModel customerModel;

    @GetMapping("/dashboard")
    public String getAllCustomer(Model model, @RequestParam(defaultValue = "0", required = false) Integer pageNo,
                                 @RequestParam(defaultValue = "2", required = false) Integer pageSize) {
        if(pageNo == null) {
            pageNo = 0;
        }

        if(pageSize == null) {
            pageSize = 10;
        }
        PageDTO<Customer> customerPageDTO=customerModel.getAll_Paging(pageNo, pageSize);
        model.addAttribute("customers", customerPageDTO);
        return "admin/dashboard";
    }
    @GetMapping("/formAdd")
    public String formAdd(Model model){
        Customer customer=new Customer();
        model.addAttribute("customer", customer);
        return "admin/formAdd";
    }
    @PostMapping("/formAdd")
    public String save(RedirectAttributes redirectAttributes, Customer customer){
        Customer customer1=customerModel.save(customer);
        if (customer1==null){
            redirectAttributes.addFlashAttribute("error", "Add failed");
            return "redirect:/admin/formAdd";
        }
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/admin/dashboard";
    }
    @GetMapping("/formEdit/{id}")
    public String formEdit(Model model,@PathVariable Long id){
        Customer customer=customerModel.findById(id);
        if(customer == null) {
            model.addAttribute("error", "Customer not found");
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("customer", customer);
        return "admin/formEdit";
    }
    @PutMapping("/formEdit")
    public String update(RedirectAttributes redirectAttributes, Customer customer){
        Customer customer1=customerModel.update(customer);
        System.out.println("CustomerController:"+customer1.getId());
        if (customer1 == null) {
            redirectAttributes.addFlashAttribute("error", "Edit failed");
            return "redirect:/admin/formEdit/" + customer.getId(); // Quay lại form nếu lỗi
        }
        redirectAttributes.addFlashAttribute("success", "Edit success");
        return "redirect:/admin/dashboard";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            customerModel.delete(id);
            redirectAttributes.addFlashAttribute("success", "Customer deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Delete failed: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }


}
