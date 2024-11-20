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
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;
import vn.edu.iuh.fit.backend.services.JobService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
//    @Autowired
//    private SkillRepository skillRepository;
//    @Autowired
//    private CandidateRepository candidateRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private JobService jobService;
//
//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address(rnd.nextInt(1, 1000) + "", "Quang Trung", "HCM", rnd.nextInt(70000, 80000) + "", CountryCode.VN);
//                addressRepository.save(add);
//                Candidate can = new Candidate("Name #" + i, LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)), add, rnd.nextLong(1111111111L, 9999999999L) + "", "candidate" + i + "@gmail.com");
//                candidateRepository.save(can);
//                System.out.println("Added: " + can);
//                Company company = new Company("About #" + i, "company" + i + "@gmail.com", "Company #" + i, rnd.nextLong(1111111111L, 9999999999L) + "", "http://company" + i + ".com", add);
//                companyRepository.save(company);
//                Job job = new Job("Job #" + i, "Software Engineering #" + i, company);
//                jobRepository.save(job);
//            }
//            String programmingLanguages[] = {"Java", "Python", "JavaScript", "C++", "C#", "Ruby", "Go", "Swift", "Kotlin", "PHP"};
//            byte type = 1;
//            for (int i = 0; i < programmingLanguages.length; i++) {
//                Skill skill = new Skill();
//                skill.setSkillName(programmingLanguages[i]);
//                skill.setSkillDescription("A programming language used for development of software.");
//                skill.setType(type);
//                // Lưu Skill vào cơ sở dữ liệu
//                skillRepository.save(skill);
//            }
//        };
//    }
}
