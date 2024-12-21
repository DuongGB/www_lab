/*
 * @ {#} DataLoader.java   1.0     12/18/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Candidate;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Experience;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.models.Role;
import vn.edu.iuh.fit.jpasecurity_candidate.backend.repositories.CandidateRepository;

import java.time.LocalDate;
import java.util.Arrays;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/18/2024
 * @version:    1.0
 */
//@Component
//public class DataLoader implements CommandLineRunner {
//    @Autowired
//    private CandidateRepository candidateRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Candidate candidate1 = new Candidate();
//        candidate1.setFullName("Nguyen Tan Thai Duong");
//        candidate1.setEmail("duongnguyenqn123@gmail.com");
//        candidate1.setPhone("0123456789");
//
//        Candidate candidate2 = new Candidate();
//        candidate2.setFullName("Nguyen Tan Thai");
//        candidate2.setEmail("duongnguyenqn12@gmail.com");
//        candidate2.setPhone("01234567899");
//
//        Candidate candidate3 = new Candidate();
//        candidate3.setFullName("Nguyen Tan");
//        candidate3.setEmail("duongnguyenqn1@gmail.com");
//        candidate3.setPhone("0123456788");
//
//        Experience experience1 = new Experience();
//        experience1.setCandidate(candidate1);
//        experience1.setCompanyName("ABC Corp");
//        experience1.setFromDate(LocalDate.of(2020, 1, 1));
//        experience1.setToDate(LocalDate.of(2022, 1, 1));
//        experience1.setRole(Role.MANAGER);
//        experience1.setWorkDescription("Managed a team of developers");
//        experience1.setCandidate(candidate1);
//        candidate1.setExperiences(Arrays.asList(experience1));
//
//        Experience experience2 = new Experience();
//        experience2.setCompanyName("XYZ Ltd");
//        experience2.setFromDate(LocalDate.of(2019, 5, 15));
//        experience2.setToDate(LocalDate.of(2021, 5, 15));
//        experience2.setRole(Role.STAFF);
//        experience2.setWorkDescription("Assisted in administrative tasks");
//        experience2.setCandidate(candidate2);
//        candidate2.setExperiences(Arrays.asList(experience2));
//
//        Experience experience3 = new Experience();
//        experience3.setCompanyName("Tech Solutions");
//        experience3.setFromDate(LocalDate.of(2021, 3, 1));
//        experience3.setToDate(LocalDate.of(2023, 3, 1));
//        experience3.setRole(Role.EXECUTIVE);
//        experience3.setWorkDescription("Implemented new software solutions");
//        experience3.setCandidate(candidate3);
//        candidate3.setExperiences(Arrays.asList(experience3));
//
//        candidateRepository.saveAll(Arrays.asList(candidate1, candidate2, candidate3));
//    }
//}

