/*
 * @ {#} JobService.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;

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

    public Job save(JobDTO jobDTO) {
        Company company = companyRepository.findById(jobDTO.getCompanyId()).orElse(null);
        Job job = new Job();
        job.setJobName(jobDTO.getJobName());
        job.setJobDesc(jobDTO.getJobDesc());
        job.setCompany(company);
        Job savedJob = jobRepository.save(job);
        for (Long skillId : jobDTO.getSkillIds()) {
            Skill skill = skillRepository.findById(skillId).orElse(null);
            JobSkill jobSkill = new JobSkill();
            JobSkillId jobSkillId = new JobSkillId();
            jobSkillId.setJobId(savedJob.getId());
            jobSkillId.setSkillId(skill.getId());
            jobSkill.setId(jobSkillId);
            jobSkill.setJob(savedJob);
            jobSkill.setSkill(skill);
            jobSkill.setSkillLevel((byte)1);
            jobSkill.setMoreInfos("More info");
            jobSkillRepository.save(jobSkill);
        }
        return savedJob;
    }
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
}

