/*
 * @ {#} CandidateController.java   1.0     12/18/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Candidate;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.services.CandidateService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/18/2024
 * @version:    1.0
 */
@Controller
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/candidates")
    public String showCandidates(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        return "candidates";
    }

    @GetMapping("/can_detail")
    public String showCandidateDetails(@RequestParam Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        model.addAttribute("candidate", candidate);
        return "can_detail";
    }

    @GetMapping("/report1")
    public String getCandidatesByCompany(@RequestParam(value = "companyName",required = false) String companyName, Model model) {
        List<Candidate> candidates = candidateService.findCompanyCompanyNameAndRole(companyName, "MANAGER");
        model.addAttribute("candidates", candidates);
        return "report1";
    }

    @GetMapping("/report2")
    public String getCandidatesWithExperience(Model model) {
        List<Object[]> candidates = candidateService.getCandidatesWithMinExperience(5);
        model.addAttribute("candidates", candidates);
        return "report2";
    }
}

