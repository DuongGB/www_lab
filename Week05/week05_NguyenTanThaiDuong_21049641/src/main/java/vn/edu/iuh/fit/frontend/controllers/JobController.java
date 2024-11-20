/*
 * @ {#} JobController.java   1.0     11/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.services.CandidateService;
import vn.edu.iuh.fit.backend.services.EmailService;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/13/2024
 * @version:    1.0
 */
@Controller
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateService candidateService;
    private EmailService emailService;

    @GetMapping("/jobs")
    public String showJobListPaging(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Job> jobPage = jobService.findAll(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("jobPage", jobPage);
        int totalPages = (jobPage).getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "jobs/jobs-paging";
    }

    @GetMapping("/searchJobByJobName")
    public String searchByJobName(Model model, @RequestParam("jobName") String jobName) {
        List<Job> jobs = jobService.findByJobNameContaining(jobName);
        model.addAttribute("jobs", jobs);
        Page<Job> jobPage = new PageImpl<>(jobs);
        model.addAttribute("jobPage", jobPage);
        return "jobs/jobs-paging";
    }

    @GetMapping("jobs/recommendations")
    public String getJobRecommendations(@SessionAttribute("email") String email, Model model) {
        if (email != null) {
            List<Job> recommendedJobs = jobService.recommendJobsForCandidate(email);
            Candidate candidate = candidateService.findByEmail(email);
            model.addAttribute("candidate", candidate);
            model.addAttribute("jobs", recommendedJobs);
            model.addAttribute("email", email);
            return "candidates/dashboard-candidate";
        } else {
            return "redirect:/login";
        }
    }

    // Hiển thị chi tiết công việc với jobId
    @GetMapping("/jobs/detail/{jobId}")
    public String showJobDetail(@PathVariable Long jobId, Model model) {
        Job job = jobService.findById(jobId);
        model.addAttribute("job", job);
        return "jobs/job-detail";
    }

    // Hiển thị trang ứng tuyển công việc với jobId  và CandidateId
    @GetMapping("jobs/{jobId}/invite")
    public String showCandidateForJob(@PathVariable Long jobId, Model model) {
        Job job = jobService.findById(jobId);
        List<Candidate> candidates = candidateService.findCandidatesForJob(job);
        model.addAttribute("job", job);
        model.addAttribute("candidates", candidates);
        return "jobs/invite-candidate";
    }

    // Mời ứng viên ứng tuyển vào công việc với jobId và CandidateId
    @PostMapping("jobs/{jobId}/invite/{CandidateId}")
    public String inviteCandidate(@PathVariable Long jobId, @PathVariable Long CandidateId) {
        Candidate candidate = candidateService.findById(CandidateId);
        Job job = jobService.findById(jobId);
        if (candidate == null) {
            throw new RuntimeException("Candidate not found");
        }
        if (job == null) {
            throw new RuntimeException("Job not found");
        }
        String toEmail = candidate.getEmail();
        String subject = "Job Invitation from " + job.getCompany().getCompName() + " for job " + job.getJobName();
        String content = "Dear " + candidate.getFullName() + ",\n" +
                "We would like to invite you to apply for the job " + job.getJobName() + " at our company " + job.getCompany().getCompName() + ".\n" +
                "Please click on the link below to apply for the job:\n" +
                "http://localhost:8080/jobs/" + job.getId() + "/apply\n" +
                "Best regards,\n" +
                job.getCompany().getCompName();
        emailService.sendInvitationEmail(toEmail, subject, content);
        return "redirect:/jobs" + jobId + "/invite" + CandidateId;
    }
}

