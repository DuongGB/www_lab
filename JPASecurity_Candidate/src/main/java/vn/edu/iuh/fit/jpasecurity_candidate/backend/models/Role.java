/*
 * @ {#} Role.java   1.0     12/18/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate.backend.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/18/2024
 * @version:    1.0
 */
public enum Role {
    ADMINISTRATOR,
    STAFF,
    MANAGER,
    EXECUTIVE
}

