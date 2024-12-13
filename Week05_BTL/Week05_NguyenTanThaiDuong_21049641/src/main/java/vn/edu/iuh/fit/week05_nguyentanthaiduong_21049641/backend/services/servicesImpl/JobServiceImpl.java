/*
 * @ {#} JobServiceImpl.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.servicesImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.JobRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.JobService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Page<Job> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findAll(pageable);
    }

    @Override
    public List<Job> findByCompanyWithEmail(String email) {
        return jobRepository.findJobsByCompany_Email(email);
    }

    @Override
    public Page<Job> searchJobs(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return jobRepository.findAll(pageable);
        }
        return jobRepository.findByJobNameContainingIgnoreCaseOrCompany_CompNameContainingIgnoreCaseOrJobSkills_Skill_SkillNameContainingIgnoreCase(search, search, search, pageable);
    }

    @Override
    public List<Job> serachJobs(String query, Long companyId) {
        return jobRepository.searchByJobNameOrSkillName(query, companyId);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void sendApplication(String jobId, String appName, String email, String message, Job job, Candidate candidate) throws MessagingException {
        // get email of company
        String companyEmail = job.getCompany().getEmail();
        String subject = "Application for " + job.getJobName();
        // content of email
        StringBuilder body = new StringBuilder();
        body.append("<html>")
                .append("<head>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; }")
                .append("h3, h4 { color: #2c3e50; }")
                .append("p { margin: 5px 0; }")
                .append("ul { margin: 10px 0; padding-left: 20px; }")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<h3>Job Application Details</h3>")
                .append("<p><strong>Applicant Name:</strong> ").append(candidate.getFullName()).append("</p>")
                .append("<p><strong>Email:</strong> ").append(candidate.getEmail()).append("</p>")
                .append("<p><strong>Phone:</strong> ").append(candidate.getPhone()).append("</p>")
                .append("<p><strong>Date of Birth:</strong> ").append(candidate.getDob()).append("</p>")
                .append("<h4>Candidate Address</h4>")
                .append("<p>").append(candidate.getAddress().getFullAddress()).append("</p>")
                .append("<h4>Job Details</h4>")
                .append("<p><strong>Job Name:</strong> ").append(job.getJobName()).append("</p>")
                .append("<p><strong>Job Description:</strong></p>")
                .append("<p>").append(job.getJobDesc()).append("</p>")
                .append("<h4>Required Skills</h4>")
                .append("<ul>");

        for (JobSkill jobSkill : job.getJobSkills()) {
            body.append("<li>")
                    .append(jobSkill.getSkill().getSkillName())
                    .append(" - Level: ").append(jobSkill.getSkillLevel())
                    .append("</li>");
        }

        body.append("</ul>")
                .append("<h4>Company Information</h4>")
                .append("<p><strong>Company Name:</strong> ").append(job.getCompany().getCompName()).append("</p>")
                .append("<p><strong>About:</strong> ").append(job.getCompany().getAbout()).append("</p>")
                .append("<p><strong>Email:</strong> ").append(job.getCompany().getEmail()).append("</p>")
                .append("<p><strong>Phone:</strong> ").append(job.getCompany().getPhone()).append("</p>")
                .append("<p><strong>Website:</strong> <a href=\"").append(job.getCompany().getWebUrl()).append("\">")
                .append(job.getCompany().getWebUrl()).append("</a></p>")
                .append("<p><strong>Address:</strong> ").append(job.getCompany().getAddress().getFullAddress()).append("</p>")
                .append("<h4>Message:</h4>")
                .append("<p>").append(message).append("</p>")
                .append("</body>")
                .append("</html>");

// Tạo MimeMessage
        MimeMessage mess = javaMailSender.createMimeMessage();
        mess.setHeader("Content-Type", "text/html; charset=UTF-8");

// Cấu hình MimeMessageHelper
        MimeMessageHelper helper = new MimeMessageHelper(mess, true, "UTF-8");
        helper.setTo(companyEmail);
        helper.setSubject(subject);
        helper.setText(body.toString(), true); // Set true để chỉ định nội dung là HTML

// Gửi email
        javaMailSender.send(mess);
    }
}

