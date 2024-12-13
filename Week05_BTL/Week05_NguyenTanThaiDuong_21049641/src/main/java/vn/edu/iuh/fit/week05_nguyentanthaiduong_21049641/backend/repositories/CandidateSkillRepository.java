/*
 * @ {#} CandidateSkillRepository.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.CandidateSkillId;

import java.util.List;
import java.util.Objects;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    CandidateSkill findByCanIdAndSkillId(Long canId, Long skillId);

    List<CandidateSkill> findByCanId(Long canId);

    @Query("SELECT cs.skill.skillName, count(cs.skill.skillName) as total FROM CandidateSkill cs GROUP BY cs.skill.skillName ORDER BY total DESC")
    List<Object[]> findTopSkillsInCandidates();
}

