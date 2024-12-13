/*
 * @ {#} JobRepository.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobsByCompany_Email(String email);

    @Query("SELECT j FROM Job j WHERE EXISTS (" +
            "SELECT 1 FROM JobSkill js WHERE js.job = j " +
            "AND js.skill IN (SELECT cs.skill FROM CandidateSkill cs WHERE cs.can.email = :email))")
    List<Job> findRecommendedJobsForCandidate(@Param("email") String email);

    @Query("SELECT j FROM Job j " +
            "JOIN j.jobSkills js " +
            "WHERE j.company.id = :companyId " +
            "AND (LOWER(j.jobName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(js.skill.skillName) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Job> searchByJobNameOrSkillName(@Param("query") String query, @Param("companyId") Long companyId);

    Page<Job> findByJobNameContainingIgnoreCaseOrCompany_CompNameContainingIgnoreCaseOrJobSkills_Skill_SkillNameContainingIgnoreCase(
            String jobName, String companyName, String skillName, Pageable pageable);
}

