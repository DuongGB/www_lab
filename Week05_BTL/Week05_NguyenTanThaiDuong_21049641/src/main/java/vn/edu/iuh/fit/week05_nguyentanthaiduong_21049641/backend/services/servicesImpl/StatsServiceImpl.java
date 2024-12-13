/*
 * @ {#} StatsServiceImpl.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services.StatsService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Override
    public Map<String, Long> getTopSkillsInJob() {
        List<Object[]> results = jobSkillRepository.findTopSkillsInJobs();
        return results.stream()
                .sorted((o1, o2) -> Long.compare((Long) o2[1], (Long) o1[1])) // Sắp xếp giảm dần theo số lượng
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1],
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new // Giữ thứ tự đã sắp xếp
                ));
    }

    @Override
    public Map<String, Long> getTopSkillsInCandidate() {
        List<Object[]> results = candidateSkillRepository.findTopSkillsInCandidates();
        return results.stream()
                .sorted((o1, o2) -> Long.compare((Long) o2[1], (Long) o1[1])) // Sắp xếp giảm dần theo số lượng
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1],
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new // Giữ thứ tự đã sắp xếp
                ));
    }
}

