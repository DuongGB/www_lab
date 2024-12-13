/*
 * @ {#} SkillServiceImpl.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Skill;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.SkillService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll().stream()
                .sorted(Comparator.comparing(Skill::getId))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public List<Skill> recommendSkillsForCandidate(Long candidateId) {
        Pageable pageable = PageRequest.of(0, 5);
        return skillRepository.findSkillsNotInCandidateSkills(candidateId, pageable);
    }

    @Override
    public Skill findBySkillName(String skillName) {
        return skillRepository.findBySkillName(skillName);
    }

    @Override
    public void save(Skill skill) {
        skillRepository.save(skill);
    }
}

