/*
 * @ {#} CompanySerVice.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services;

import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Company;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public interface CompanySerVice {
    public Company findById(Long id);

    public Company findByEmail(String email);

    public boolean existsByPhone(String phone);

    public boolean existsByEmail(String email);

    public Company save(Company company);
}

