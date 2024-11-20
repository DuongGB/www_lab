/*
 * @ {#} JobService.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;
import java.util.stream.Collectors;


/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */
@Repository
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Page<Job> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findAll(pageable);
    }

    public List<Job> findByJobNameContaining(String jobName) {
        return jobRepository.findByJobNameContaining(jobName);
    }

    // Tìm các kỹ năng mà ứng viên có dựa trên email, sau đó tìm các công việc mà có ít nhất một trong các kỹ năng đó, trả về danh sách các công việc
    public List<Job> recommendJobsForCandidate(String email) {
        return jobRepository.findRecommendedJobsForCandidate(email);
    }


}

