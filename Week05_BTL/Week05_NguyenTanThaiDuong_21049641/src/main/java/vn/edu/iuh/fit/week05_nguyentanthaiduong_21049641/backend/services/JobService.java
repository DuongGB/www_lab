/*
 * @ {#} JobService.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services;

import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public interface JobService {
    public Page<Job> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

    public List<Job> findByCompanyWithEmail(String email);

    public Page<Job> searchJobs(String search, Pageable pageable);

    public List<Job> searchJobs(String query, Long companyId);

    public Job findById(Long id);

    public Job save(Job job);

    public void deleteById(Long id);

    public List<Job> recommendJobsForCandidate(String email);

    public void sendApplication(String jobId, String appName, String email, String message, Job job, Candidate candidate) throws MessagingException;

}

