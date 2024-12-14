/*
 * @ {#} CompanyController.java   1.0     12/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.converters.SkillLevelConverter;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.enums.SkillType;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.*;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.CompanySerVice;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.JobService;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.JobSkillService;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.SkillService;

import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/14/2024
 * @version:    1.0
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanySerVice companySerVice;
    @Autowired
    private JobService jobService;
    @Autowired
    private JobSkillService jobSkillService;
    @Autowired
    private SkillService skillservice;

    @GetMapping("/jobs")
    public String showJobs(@SessionAttribute("email") String email, Model model) {
        Company company = companySerVice.findByEmail(email);
        List<Job> jobPosts = jobService.findByCompanyWithEmail(email);
        for (Job job : jobPosts) {
            List<JobSkill> jobSkills = jobSkillService.findByJob(job);
            job.setJobSkills(jobSkills);
        }
        model.addAttribute("company", company);
        model.addAttribute("jobPosts", jobPosts);
        return "company/jobs-company";
    }

    @GetMapping("/signUpCompany")
    public String signUpForm(Model model) {
        model.addAttribute("company", new Company());
        List<CountryCode> countries = List.of(CountryCode.values());
        model.addAttribute("countries", countries);
        return "home/signUpCompany";
    }

    @PostMapping("/signUpCompany")
    public String createCompany(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        if (companySerVice.existsByEmail(company.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email already exists!");
            return "redirect:/company/signUpCompany";
        }
        if (companySerVice.existsByPhone(company.getPhone())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phone number already exists!");
            return "redirect:/company/signUpCompany";
        }
        companySerVice.save(company);
        redirectAttributes.addFlashAttribute("successMessage", "Company registered successfully!");
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {
        Company company = companySerVice.findById(id);
        if (company == null) {
            throw new RuntimeException("Company not found!");
        }
        model.addAttribute("company", company);
        List<CountryCode> countries = List.of(CountryCode.values());
        model.addAttribute("countries", countries);
        return "company/edit-company";
    }

    @PostMapping("/edit/{id}")
    public String editCompany(@PathVariable Long id, @ModelAttribute("company") Company company, HttpSession session, RedirectAttributes redirectAttributes) {
        Company existingCompany = companySerVice.findById(id);
        if (existingCompany == null) {
            redirectAttributes.addFlashAttribute("message", "Company not found!");
            return "redirect:/company/jobs";
        }
        existingCompany.setCompName(company.getCompName());
        existingCompany.setEmail(company.getEmail());
        existingCompany.setPhone(company.getPhone());
        existingCompany.setAbout(company.getAbout());
        existingCompany.setWebUrl(company.getWebUrl());
        existingCompany.setAddress(company.getAddress());
        companySerVice.save(existingCompany);
        session.setAttribute("email", existingCompany.getEmail());
        redirectAttributes.addFlashAttribute("successMessage", "Company updated successfully!");
        return "redirect:/company/jobs";
    }

    @GetMapping("/newJob")
    public String createNewJob(@SessionAttribute("email") String email, Model model) {
        Company company = companySerVice.findByEmail(email);
        Job job = new Job();
        job.setCompany(company);
        job.setJobSkills(new ArrayList<>());
        model.addAttribute("job", job);
        model.addAttribute("skills", skillservice.findAll());
        model.addAttribute("company", company);
        return "company/addJob-company";
    }

    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute("job") Job job,
                          @RequestParam(value = "newSkillNames", required = false) List<String> newSkillNames,
                          @RequestParam(value = "newSkillLevels", required = false) List<String> newSkillLevels,
                          @RequestParam(value = "newSkillMoreInfos", required = false) List<String> newSkillMoreInfos) {
        // Nếu danh sách jobSkills chưa tồn tại, khởi tạo
        if (job.getJobSkills() == null) {
            job.setJobSkills(new ArrayList<>());
        }
        // Lưu Job trước để đảm bảo có jobId
        jobService.save(job);
        if (newSkillNames != null && newSkillLevels != null) {
            for (int i = 0; i < newSkillNames.size(); i++) {
                String skillName = newSkillNames.get(i).trim();
                Byte skillLevelByte = Byte.valueOf(newSkillLevels.get(i));
                SkillLevelConverter converter = new SkillLevelConverter();
                SkillLevel skillLevel = converter.convertToEntityAttribute(skillLevelByte);
                String moreInfo = (newSkillMoreInfos != null && newSkillMoreInfos.size() > i)
                        ? newSkillMoreInfos.get(i).trim()
                        : "";
                if (!skillName.isEmpty()) {
                    Skill skill = skillservice.findBySkillName(skillName);
                    if (skill == null) {
                        skill = new Skill();
                        skill.setSkillName(skillName);
                        skill.setSkillDescription("A skill for job"); // default description
                        skill.setType(SkillType.SOFT_SKILL);
                        skillservice.save(skill);
                    }
                    JobSkill jobSkill = new JobSkill();
                    JobSkillId jobSkillId = new JobSkillId();
                    // Thiết lập composite key
                    jobSkillId.setJobId(job.getId());
                    jobSkillId.setSkillId(skill.getId());
                    jobSkill.setId(jobSkillId);
                    // Thiết lập các thuộc tính khác
                    jobSkill.setSkill(skill);
                    jobSkill.setSkillLevel(skillLevel);
                    jobSkill.setMoreInfos(moreInfo);
                    jobSkill.setJob(job);
                    // Thêm vào danh sách jobSkills của Job
                    job.getJobSkills().add(jobSkill);
                }
            }
        }
        job.getJobSkills().removeIf(js -> js.getSkill() == null || js.getSkillLevel() == null);
        jobService.save(job);
        return "redirect:/company/jobs";
    }


    @PostMapping("/{id}/delete")
    public String deleteJob(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Job job = jobService.findById(id);
        if (job == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Job not found!");
            return "redirect:/company/jobs";
        }
        jobService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Job deleted successfully!");
        return "redirect:/company/jobs";
    }
}

