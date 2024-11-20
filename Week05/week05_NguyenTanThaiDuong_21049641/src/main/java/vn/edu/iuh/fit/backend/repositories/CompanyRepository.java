/*
 * @ {#} CompanyRepository.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Company;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByCompNameContaining(String compName);

    Optional<Company> findByEmail(String email);

    boolean existsByCompNameOrEmail(String companyName, String companyEmail);
}
