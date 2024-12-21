/*
 * @ {#} CandidateRepository.java   1.0     12/18/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Candidate;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/18/2024
 * @version:    1.0
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c FROM Candidate c JOIN c.experiences e WHERE e.companyName = :companyName AND e.role = 'MANAGER'")
    List<Candidate> findByCompanyNameAndRole(String companyName, String role);
}
