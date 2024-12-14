/*
 * @ {#} JobController.java   1.0     12/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.frontend.controllers;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.converters.SkillLevelConverter;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.enums.SkillType;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.*;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/14/2024
 * @version:    1.0
 */
@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobSkillService jobSkillService;
    @Autowired
    private CompanySerVice companySerVice;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/{id}/edit")
    private String editJob(@PathVariable("id") Long id, Model model) {
        Job job = jobService.findById(id);
        List<Skill> skills = job.getJobSkills().stream()
                .map(JobSkill::getSkill)
                .collect(Collectors.toList());
        if (job != null) {
            if (job.getJobSkills() == null) {
                job.setJobSkills(new ArrayList<>());
            }
            List<Long> selectedSkillIds = job.getJobSkills().stream()
                    .map(jobSkill -> jobSkill.getSkill().getId())
                    .collect(Collectors.toList());
            model.addAttribute("job", job);
            model.addAttribute("skills", skills);
            model.addAttribute("selectedSkillIds", selectedSkillIds);
            return "jobs/edit-job";
        } else {
            return "redirect:/company/jobs";
        }
    }

    @PostMapping("/{id}/edit")
    public String editJob(@PathVariable("id") Long id,
                          @ModelAttribute("job") Job job,
                          @RequestParam(value = "newSkillNames", required = false) List<String> newSkillNames,
                          @RequestParam(value = "newSkillLevels", required = false) List<Byte> newSkillLevels,
                          @RequestParam(value = "newSkillMoreInfos", required = false) List<String> newSkillMoreInfos,
                          RedirectAttributes redirectAttributes) {

        Job existingJob = jobService.findById(id);
        if (existingJob == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Job not found!");
            return "redirect:/company/jobs";
        }

        existingJob.setJobName(job.getJobName());
        existingJob.setJobDesc(job.getJobDesc());
        existingJob.setCompany(job.getCompany());
        existingJob.getJobSkills().clear();

        if (newSkillNames != null && newSkillLevels != null && newSkillMoreInfos != null) {
            for (int i = 0; i < newSkillNames.size(); i++) {
                String skillName = newSkillNames.get(i).trim();
                Byte skillLevelByte = newSkillLevels.get(i);
                SkillLevel skillLevel = new SkillLevelConverter().convertToEntityAttribute(skillLevelByte);
                String moreInfo = newSkillMoreInfos.get(i).trim();

                if (!skillName.isEmpty()) {
                    Skill skill = skillService.findBySkillName(skillName);
                    if (skill == null) {
                        skill = new Skill();
                        skill.setSkillName(skillName);
                        skill.setSkillDescription("A skill for job");
                        skill.setType(SkillType.SOFT_SKILL);
                        skillService.save(skill);
                    }

                    JobSkill jobSkill = new JobSkill();
                    jobSkill.setSkill(skill);
                    jobSkill.setSkillLevel(skillLevel);
                    jobSkill.setMoreInfos(moreInfo);
                    jobSkill.setJob(existingJob);

                    JobSkillId jobSkillId = new JobSkillId();
                    jobSkillId.setJobId(existingJob.getId());
                    jobSkillId.setSkillId(skill.getId());
                    jobSkill.setId(jobSkillId);

                    existingJob.getJobSkills().add(jobSkill);
                }
            }
        }

        jobService.save(existingJob);
        redirectAttributes.addFlashAttribute("successMessage", "Job updated successfully!");
        return "redirect:/company/jobs";
    }


    @GetMapping("/search")
    public String search(@RequestParam("query") String query, @RequestParam("companyId") Long companyId, Model model) {
        Company company = companySerVice.findById(companyId);
        model.addAttribute("company", company);
        List<Job> jobPostings = jobService.searchJobs(query, companyId);
        model.addAttribute("jobPostings", jobPostings);
        model.addAttribute("query", query);
        return "company/jobs-company";
    }

    @GetMapping("/detail/{id}")
    public String showJobDetail(@PathVariable Long id, Model model) {
        Job job = jobService.findById(id);
        model.addAttribute("job", job);
        return "jobs/detail-job";
    }

    @GetMapping("/apply/{id}")
    public String showFormApply(@PathVariable Long id, @SessionAttribute("email") String email, Model model) {
        Job job = jobService.findById(id);
        model.addAttribute("job", job);
        model.addAttribute("jobId", id);
        Candidate candidate = candidateService.findByEmail(email);
        model.addAttribute("candidate", candidate);
        return "candidates/apply-candidate";
    }

    @PostMapping("/apply/send")
    public String submitApply(@RequestParam String jobId,
                              @RequestParam String applicantName,
                              @RequestParam String email,
                              @RequestParam String message,
                              RedirectAttributes redirectAttributes) throws MessagingException {
        Job job = jobService.findById(Long.valueOf(jobId));
        Candidate candidate = candidateService.findByEmail(email);
        if (candidate == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Candidate not found!");
            return "redirect:/jobs/apply/" + jobId;
        }
        jobService.sendApplication(jobId, applicantName, email, message, job, candidate);
        redirectAttributes.addFlashAttribute("successMessage", "Application sent successfully!");
        return "redirect:/jobs/apply/" + jobId;
    }

    @GetMapping("/{jobId}/find")
    public String findCandidateForJob(@PathVariable Long jobId, Model model) {
        Job job = jobService.findById(jobId);
        List<Candidate> candidates = candidateService.findCandidatesForJob(job);
        model.addAttribute("job", job);
        model.addAttribute("candidates", candidates);
        return "company/findCan-company";
    }

    @PostMapping("/{jobId}/invite/{candidateId}")
    public String inviteCandidate(@PathVariable Long jobId, @PathVariable Long candidateId, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.findById(candidateId);
        if (candidate == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Candidate not found!");
        }
        Job job = jobService.findById(jobId);
        if (job == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Job not found!");
        }
        String email = candidate.getEmail();
        String subject = "Invitation to apply for" + job.getJobName();
        String body = "Dear " + candidate.getFullName() + ",\n\n" +
                "We would like to invite you to apply for the job " + job.getJobName() + ".\n\n" +
                "Please click on the link below to apply for the job:\n" +
                "http://localhost:8080/jobs/apply/" + jobId + "\n\n" +
                "Best regards,\n" +
                job.getCompany().getCompName();
        emailService.sendInvitationEmail(email, subject, body);
        redirectAttributes.addFlashAttribute("successMessage", "Invitation sent successfully!");
        return "redirect:/jobs/" + jobId + "/find";
    }

}

