/*
 * @ {#} CandidateController.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.enums.SkillType;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.*;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.ExperienceRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.CandidateService;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.EmailService;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.JobService;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.SkillService;

import java.util.ArrayList;
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
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }

    @GetMapping("/list-paging")
    public String showCandidateListPaging(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage = candidateService.findAll(
                currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }

    @GetMapping("/signUpCandidate")
    public String signUpForm(Model model) {
        Candidate candidate = new Candidate();
        List<CountryCode> countries = List.of(CountryCode.values());
        List<Skill> skills = skillService.findAll();
        model.addAttribute("candidate", candidate);
        model.addAttribute("countries", countries);
        model.addAttribute("skills", skills);
        return "home/signUpCandidate";
    }

    @PostMapping("/signUpCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate,
                                  @RequestParam List<Long> skillIds,
                                  @RequestParam List<Byte> skillLevels,
                                  @RequestParam(required = false) List<String> newSkillNames,
                                  @RequestParam(required = false) List<Byte> newSkillLevels,
                                  @RequestParam(required = false) List<String> newSkillMoreInfos,
                                  RedirectAttributes redirectAttributes) {
        if (candidateService.existsByPhone(candidate.getPhone())) {
            redirectAttributes.addFlashAttribute("message", "Phone number already exists");
            return "redirect:candidate/signUpCandidate";
        }
        if (candidateService.existsByEmail(candidate.getEmail())) {
            redirectAttributes.addFlashAttribute("message", "Email already exists");
            return "redirect:candidate/signUpCandidate";
        }
        if (candidateService.existsByFullName(candidate.getFullName())) {
            redirectAttributes.addFlashAttribute("message", "Full name already exists");
            return "redirect:candidate/signUpCandidate";
        }
        if (candidate.getCandidateSkills() == null) {
            candidate.setCandidateSkills(new ArrayList<>());
        }
        if (newSkillNames != null && !newSkillNames.isEmpty()) {
            for (int i = 0; i < newSkillNames.size(); i++) {
                String skillName = newSkillNames.get(i);
                Byte skillLevel = newSkillLevels != null ? newSkillLevels.get(i) : 1; // default level is 1 if not provided
                String moreInfo = newSkillMoreInfos != null ? newSkillMoreInfos.get(i) : ""; // default more info is empty if not provided
                if (skillName != null && !skillName.trim().isEmpty()) {
                    // check if skill exists
                    Skill existSkill = skillService.findBySkillName(skillName.trim());
                    Skill newSkill = null;
                    if (existSkill == null) {
                        newSkill = new Skill();
                        newSkill.setSkillName(skillName.trim());
                        newSkill.setSkillDescription("A programming language or a tool that you know well");
                        newSkill.setType(SkillType.SOFT_SKILL);
                        skillService.save(newSkill);
                    } else {
                        newSkill = existSkill;
                    }
                    CandidateSkill candidateSkill = new CandidateSkill();
                    CandidateSkillId candidateSkillId = new CandidateSkillId();
                    candidateSkillId.setCanId(candidate.getId());
                    candidateSkillId.setSkillId(newSkill.getId());
                    candidateSkill.setId(candidateSkillId);
                    candidateSkill.setCan(candidate);
                    candidateSkill.setSkill(newSkill);
                    candidateSkill.setSkillLevel(skillLevel);
                    candidateSkill.setMoreInfos(moreInfo);
                    // add to candidate's skills
                    candidate.getCandidateSkills().add(candidateSkill);
                }
            }
        }
        // Get skills from skillIds and skillLevels and save to candidate
        for (int i = 0; i < skillIds.size(); i++) {
            Long skillId = skillIds.get(i);
            Byte skillLevel = skillLevels.get(i);
            Skill skill = skillService.findById(skillId);
            // Create candidate for skill
            CandidateSkill candidateSkill = new CandidateSkill();
            CandidateSkillId candidateSkillId = new CandidateSkillId();
            candidateSkillId.setCanId(candidate.getId());
            candidateSkillId.setSkillId(skill.getId());
            candidateSkill.setId(candidateSkillId);
            candidateSkill.setCan(candidate);
            candidateSkill.setSkill(skill);
            candidateSkill.setSkillLevel(skillLevel);
            // Add to candidate's skills
            candidate.getCandidateSkills().add(candidateSkill);
        }
        candidateService.save(candidate);
        redirectAttributes.addFlashAttribute("message", "Sign up successfully");
        return "redirect:/login";
    }

    @GetMapping("/recommendations")
    public String getJobRecommendations(@SessionAttribute("email") String email, Model model) {
        if (email != null) {
            List<Job> recommendedJobs = jobService.recommendJobsForCandidate(email);
            Candidate candidate = candidateService.findByEmail(email);
            model.addAttribute("candidate", candidate);
            model.addAttribute("jobs", recommendedJobs);
            model.addAttribute("email", email);
            return "candidates/recommendations-candidate";
        }
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String editCandidate(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.findById(id);
        if (candidate == null) {
            redirectAttributes.addFlashAttribute("message", "Candidate not found");
            return "redirect:/candidate/recommendations";
        }
        List<CountryCode> countries = List.of(CountryCode.values());
        List<Experience> experiences = candidate.getExperiences();
        if (experiences.isEmpty()) {
            experiences.add(new Experience());
        }
        List<Skill> skills = skillService.findAll();
        model.addAttribute("candidate", candidate);
        model.addAttribute("countries", countries);
        model.addAttribute("skills", skills);
        return "candidates/edit-candidate";
    }

    @PostMapping("/edit/{id}")
    public String editCandidate(@PathVariable Long id,
                                  @ModelAttribute Candidate updatedCandidate,
                                  @RequestParam(required = false) List<Long> skillIds,
                                  @RequestParam(required = false) List<Byte> skillLevels,
                                  @RequestParam(required = false) List<String> moreInfos,
                                  @RequestParam(required = false) List<String> newSkillNames,
                                  @RequestParam(required = false) List<Byte> newSkillLevels,
                                  @RequestParam(required = false) List<String> newSkillMoreInfos,
                                  RedirectAttributes redirectAttributes) {
        Candidate existCandidate = candidateService.findById(id);
        if (existCandidate == null) {
            redirectAttributes.addFlashAttribute("message", "Candidate not found");
            return "redirect:/candidate/recommendations";
        }
        existCandidate.setFullName(updatedCandidate.getFullName());
        existCandidate.setDob(updatedCandidate.getDob());
        existCandidate.setEmail(updatedCandidate.getEmail());
        existCandidate.setPhone(updatedCandidate.getPhone());

        // Update skills
        if (skillIds != null && !skillIds.isEmpty()) {
            for (int i = 0; i < skillIds.size(); i++) {
                Long skillId = skillIds.get(i);
                Byte skillLevel = skillLevels.get(i);
                String moreInfo = (moreInfos != null && moreInfos.size() > i) ? moreInfos.get(i) : "";
                Skill skill = skillService.findById(skillId);
                CandidateSkill existCandidateSkill = candidateSkillRepository.findByCanIdAndSkillId(existCandidate.getId(), skill.getId());
                if (existCandidateSkill != null) {
                    existCandidateSkill.setSkillLevel(skillLevel);
                    existCandidateSkill.setMoreInfos(moreInfo);
                    candidateSkillRepository.save(existCandidateSkill);
                } else {
                    CandidateSkill candidateSkill = new CandidateSkill();
                    CandidateSkillId candidateSkillId = new CandidateSkillId();
                    candidateSkillId.setCanId(existCandidate.getId());
                    candidateSkillId.setSkillId(skill.getId());
                    candidateSkill.setId(candidateSkillId);
                    candidateSkill.setCan(existCandidate);
                    candidateSkill.setSkill(skill);
                    candidateSkill.setSkillLevel(skillLevel);
                    candidateSkill.setMoreInfos(moreInfo);
                    existCandidate.getCandidateSkills().add(candidateSkill);
                }
            }
        }

        // Update new skills
        if (newSkillNames != null && !newSkillNames.isEmpty()) {
            for (int i = 0; i < newSkillNames.size(); i++) {
                String skillName = newSkillNames.get(i);
                Byte skillLevel = (newSkillLevels != null && newSkillLevels.size() > i) ? newSkillLevels.get(i) : 1;
                String moreInfo = (newSkillMoreInfos != null && newSkillMoreInfos.size() > i) ? newSkillMoreInfos.get(i) : "";

                Skill newSkill = skillService.findBySkillName(skillName.trim());
                if (newSkill == null) {
                    newSkill = new Skill();
                    newSkill.setSkillName(skillName);
                    newSkill.setSkillDescription("A programming language used for development of software.");
                    skillService.save(newSkill);
                }

                CandidateSkill candidateSkill = new CandidateSkill();
                CandidateSkillId candidateSkillId = new CandidateSkillId();
                candidateSkillId.setCanId(existCandidate.getId());
                candidateSkillId.setSkillId(newSkill.getId());
                candidateSkill.setId(candidateSkillId);
                candidateSkill.setCan(existCandidate);
                candidateSkill.setSkill(newSkill);
                candidateSkill.setSkillLevel(skillLevel);
                candidateSkill.setMoreInfos(moreInfo);

                existCandidate.getCandidateSkills().add(candidateSkill);
                candidateSkillRepository.save(candidateSkill);
            }
        }

        // Update experiences
        List<Experience> updatedExperiences = updatedCandidate.getExperiences();
        List<Experience> existingExperiences = existCandidate.getExperiences();

        for (int i = 0; i < updatedExperiences.size(); i++) {
            Experience updatedExperience = updatedExperiences.get(i);
            Experience existingExperience = (i < existingExperiences.size()) ? existingExperiences.get(i) : new Experience();

            existingExperience.setCompany(updatedExperience.getCompany());
            existingExperience.setRole(updatedExperience.getRole());
            existingExperience.setFromDate(updatedExperience.getFromDate());
            existingExperience.setToDate(updatedExperience.getToDate());
            existingExperience.setWorkDesc(updatedExperience.getWorkDesc());
            existingExperience.setCandidate(existCandidate);

            experienceRepository.save(existingExperience);
        }

        candidateService.update(existCandidate);

        redirectAttributes.addFlashAttribute("successMessage", "Candidate updated successfully!");
        return "redirect:/candidate/recommendations";
    }
}

