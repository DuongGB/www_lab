/*
 * @ {#} SkillService.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */
@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill findById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public List<Skill> recommendSkillsForCandidate(Long candidateId) {
        Pageable pageable = PageRequest.of(0, 5);  // Lấy tối đa 5 kỹ năng
        return skillRepository.findSkillsNotInCandidateSkills(candidateId, pageable);
    }
}

