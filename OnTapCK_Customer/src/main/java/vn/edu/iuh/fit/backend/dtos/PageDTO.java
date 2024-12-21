/*
 * @ {#} PageDTO.java   1.0     21/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;

import lombok.*;

import java.util.Collection;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   21/12/2024
 * @version:    1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PageDTO<T> {
    private int page;
    private int size;
    private Collection<T> values;
    private int total;
    private int totalPages;

    private String sortBy;
    private String sortType = "ASC";
}
