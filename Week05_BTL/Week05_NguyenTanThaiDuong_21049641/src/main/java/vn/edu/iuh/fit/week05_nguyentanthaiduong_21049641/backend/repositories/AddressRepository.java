/*
 * @ {#} AddressRepository.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Address;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}

