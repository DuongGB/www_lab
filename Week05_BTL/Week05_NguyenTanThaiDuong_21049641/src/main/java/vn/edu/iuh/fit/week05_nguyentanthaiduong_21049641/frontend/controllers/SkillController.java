/*
 * @ {#} SkillController.java   1.0     12/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Skill;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.SkillService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/14/2024
 * @version:    1.0
 */
@Controller
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("recommend/{candidateId}")
    public String recommendSkills(@PathVariable Long candidateId, Model model) {
        List<Skill> recommendedSkills = skillService.recommendSkillsForCandidate(candidateId);
        model.addAttribute("recommendedSkills", recommendedSkills);
        return "candidates/skillRecommendation-candidate";
    }
}

