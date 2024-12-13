/*
 * @ {#} CandidateRepository.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Skill;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);

    @Query("SELECT  c from  Candidate  c join c.candidateSkills cs where cs.skill in :skills")
    List<Candidate> findCandidatesWithSkills(@Param("skills") List<Skill> skills);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByFullName(String fullName);
}

