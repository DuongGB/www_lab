/*
 * @ {#} CompanyServiceImpl.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Address;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Company;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.CompanySerVice;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Service
public class CompanyServiceImpl implements CompanySerVice {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company findByEmail(String email) {
        return companyRepository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return companyRepository.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    @Override
    public Company save(Company company) {
        Address address = company.getAddress();
        addressRepository.save(address);
        return companyRepository.save(company);
    }
}

