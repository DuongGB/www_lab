/*
 * @ {#} CandidateServiceImpl.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.*;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.*;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.CandidateService;

import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Override
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<Candidate> findCandidatesForJob(Job job) {
        List<Skill> requiredSkills = new ArrayList<>();
        for (JobSkill jobSkill : job.getJobSkills()) {
            requiredSkills.add(jobSkill.getSkill());
        }
        return candidateRepository.findCandidatesWithSkills(requiredSkills);
    }

    @Override
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return candidateRepository.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return candidateRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByFullName(String fullName) {
        return candidateRepository.existsByFullName(fullName);
    }

    @Override
    public void save(Candidate candidate) {
        Address address = candidate.getAddress();
        if (address != null) {
            addressRepository.save(address);
            candidate.setAddress(address);
            candidateRepository.save(candidate);
        }
    }

    @Override
    public void update(Candidate candidate) {
        Address address = candidate.getAddress();
        if (address != null) {
            addressRepository.save(address);
            candidate.setAddress(address);
        }
        for (CandidateSkill candidateSkill : candidate.getCandidateSkills()) {
            Skill skill = candidateSkill.getSkill();
            if (skill.getId() == null || !skillRepository.existsById(skill.getId())) {
                skillRepository.save(skill);
            }
            CandidateSkillId id = new CandidateSkillId();
            id.setCanId(candidate.getId());
            id.setSkillId(skill.getId());
            candidateSkill.setId(id);
            candidateSkill.setSkill(skill);
            candidateSkill.setCan(candidate);
            candidateSkillRepository.save(candidateSkill);
        }
        for (Experience experience : candidate.getExperiences()) {
            experience.setCandidate(candidate);
            experienceRepository.save(experience);
        }
        candidateRepository.save(candidate);
    }
}

