/*
 * @ {#} Role.java   1.0     12/18/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_candidate.backend.enums;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/18/2024
 * @version:    1.0
 */
public enum Role {
    ADMINISTRATOR("Administrator"),
    STAFF("Staff"),
    MANAGER("Manager"),
    EXECUTIVE("Executive");
    private final String value;

    Role(String value) {
        this.value = value;
    }
}

