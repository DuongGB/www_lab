/*
 * @ {#} CandidateService.java   1.0     12/19/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Candidate;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.repositories.ExperienceRepository;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/19/2024
 * @version:    1.0
 */
@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Candidate> findCompanyCompanyNameAndRole(String companyName, String role) {
        return candidateRepository.findByCompanyNameAndRole(companyName, role);
    }

    public List<Object[]> getCandidatesWithMinExperience(int minYears) {
        return experienceRepository.findCandidatesWithMinExperience(minYears);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Long id, Candidate candidate) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id);
        if (existingCandidate.isPresent()) {
            Candidate updatedCandidate = existingCandidate.get();
            updatedCandidate.setFullName(candidate.getFullName());
            updatedCandidate.setEmail(candidate.getEmail());
            updatedCandidate.setPhone(candidate.getPhone());
            return candidateRepository.save(updatedCandidate);
        }
        return null;
    }

    public boolean deleteCandidate(Long id) {
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

