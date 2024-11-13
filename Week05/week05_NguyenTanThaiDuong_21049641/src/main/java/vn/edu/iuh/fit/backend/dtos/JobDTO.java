/*
 * @ {#} JobDTO.java   1.0     11/9/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;

import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/9/2024
 * @version:    1.0
 */
@Getter
@Setter
// Dùng để chuyển đổi giữa kiểu dữ liệu Job và kiểu dữ liệu JobDTO để truyền dữ liệu giữa các tầng trong ứng dụng
public class JobDTO {
    private Long jobId;
    private String jobName;
    private String jobDesc;
    private Long companyId;
    private List<Long> skillIds;
}


