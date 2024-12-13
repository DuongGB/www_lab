/*
 * @ {#} CandidateService.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services;

import org.springframework.data.domain.Page;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.models.Job;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public interface CandidateService {
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);
    public Candidate findByEmail(String email);
    public List<Candidate> findCandidatesForJob(Job job);
    public Candidate findById(Long id);
    public boolean existsByPhone(String phone);
    public boolean existsByEmail(String email);
    public void save(Candidate candidate);
    public void update(Candidate candidate);

}

