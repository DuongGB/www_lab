/*
 * @ {#} HangXeDao.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.HangXe;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
public interface HangXeDao {
    public List<HangXe> getDsHangXe();

    public HangXe findByID(int id);
}
