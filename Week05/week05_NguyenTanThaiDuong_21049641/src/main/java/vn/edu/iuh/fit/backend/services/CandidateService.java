/*
 * @ {#} CandidateService.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;

import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */
@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    public List<Candidate> findByNameContaining(String fullName) {
        return candidateRepository.findByFullNameContaining(fullName);
    }

    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email).orElse(null);
    }

    public List<Candidate> findCandidatesForJob(Job job) {
        List<Skill> requiredSkills = new ArrayList<>();
        // Lấy ra tất cả kỹ năng của công việc
        for (JobSkill jobSkill : job.getJobSkills()) {
            requiredSkills.add(jobSkill.getSkill());
        }
        return candidateRepository.findCandidatesWithSkills(requiredSkills);
    }

    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }
}

