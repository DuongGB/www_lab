/*
 * @ {#} StatsService.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.services;

import java.util.Map;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public interface StatsService {
    public Map<String, Long> getTopSkillsInJob();

    public Map<String, Long> getTopSkillsInCandidate();
}

