/*
 * @ {#} JobSkillService.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services;

import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.JobSkill;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public interface JobSkillService {
    public List<JobSkill> findByJob(Job job);

    public void save(JobSkill jobSkill);
}

