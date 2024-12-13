/*
 * @ {#} JobSkillServiceImpl.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.JobSkillService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Service
public class JobSkillServiceImpl implements JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Override
    public List<JobSkill> findByJob(Job job) {
        return jobSkillRepository.findByJob(job);
    }

    @Override
    public void save(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }
}

