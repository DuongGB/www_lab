/*
 * @ {#} SkillRepository.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Skill;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("SELECT s FROM Skill s WHERE s NOT IN " +
            "(SELECT cs.skill FROM CandidateSkill cs WHERE cs.can.id = :candidateId)")
    List<Skill> findSkillsNotInCandidateSkills(@Param("candidateId") Long candidateId, Pageable pageable);

    Skill findBySkillName(String skillName);
}

