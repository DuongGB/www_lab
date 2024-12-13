/*
 * @ {#} SkillService.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Skill;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public interface SkillService {
    public List<Skill> findAll();

    public Skill findById(Long id);

    public List<Skill> recommendSkillsForCandidate(Long candidateId);

    public Skill findBySkillName(String skillName);

    public void save(Skill skill);
}

