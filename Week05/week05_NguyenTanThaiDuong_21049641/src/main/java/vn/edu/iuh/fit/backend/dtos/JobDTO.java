/*
 * @ {#} JobDTO.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */
@Getter
@Setter
public class JobDTO {
    private Long jobId;
    private String jobName;
    private String jobDesc;
    private Long companyId;
    private List<Long> skillIds;
}

