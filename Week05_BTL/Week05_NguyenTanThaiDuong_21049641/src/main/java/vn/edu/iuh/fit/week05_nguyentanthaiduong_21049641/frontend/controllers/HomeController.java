/*
 * @ {#} HomeController.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Company;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.CandidateService;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.CompanySerVice;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.JobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Controller
@SessionAttributes("email")
public class HomeController {
    @Autowired
    private CompanySerVice companySerVice;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobService jobService;

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, Model model) {
        Candidate candidate = candidateService.findByEmail(email);
        Company company = companySerVice.findByEmail(email);
        if (candidate != null) {
            model.addAttribute("email", email);
            return "redirect:/candidate";
        } else if (company != null) {
            model.addAttribute("email", email);
            return "redirect:/company";
        } else {
            model.addAttribute("message", "Invalid email");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("email"); // remove session
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        Page<Job> jobPage = jobService.findAll(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("jobPage", jobPage);
        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNum = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return "index";
    }
}

