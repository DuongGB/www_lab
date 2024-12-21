/*
 * @ {#} ExperienceRepository.java   1.0     12/18/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Experience;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/18/2024
 * @version:    1.0
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    // Lấy danh sách ứng viên có ít nhất số năm kinh nghiệm
    @Query("SELECT c.fullName, c.email, c.phone, SUM(YEAR(e.toDate) - YEAR(e.fromDate)) AS totalExperience " +
            "FROM Experience e JOIN e.candidate c " +
            "GROUP BY c.id " +
            "HAVING SUM(YEAR(e.toDate) - YEAR(e.fromDate)) >= :minYears")
    List<Object[]> findCandidatesWithMinExperience(@Param("minYears") int minYears);

}
