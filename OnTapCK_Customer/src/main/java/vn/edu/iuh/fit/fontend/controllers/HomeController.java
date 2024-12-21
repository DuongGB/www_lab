/*
 * @ {#} HomeController.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}
