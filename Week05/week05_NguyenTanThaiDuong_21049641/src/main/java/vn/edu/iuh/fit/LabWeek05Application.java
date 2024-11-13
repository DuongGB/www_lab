/*
 * @ {#} LabWeek05Application.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;

import java.time.LocalDate;
import java.util.Random;
/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */

@SpringBootApplication
public class LabWeek05Application {
    public static void main(String[] args) {
        SpringApplication.run(LabWeek05Application.class, args);
    }

//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Autowired
//    private CandidateRepository candidateRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address(rnd.nextInt(1, 1000) + "", "Quang Trung", "HCM", rnd.nextInt(70000, 80000) + "", CountryCode.VN);
//                addressRepository.save(add);
//                Candidate can = new Candidate("Name #" + i, LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)), add, rnd.nextLong(1111111111L, 9999999999L) + "", "email_" + i + "@gmail.com");
//                candidateRepository.save(can);
//                System.out.println("Added: " + can);
//                Company company = new Company("About #" + i, "email_" + i + "@gmail.com", "Company #" + i, rnd.nextLong(1111111111L, 9999999999L) + "", "http://company" + i + ".com", add);
//                companyRepository.save(company);
//                Job job = new Job("Job #" + i, "Software Engineering #" + i, company);
//                jobRepository.save(job);
//            }
//        };
//    }
}
