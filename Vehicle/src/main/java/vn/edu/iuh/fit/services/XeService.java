/*
 * @ {#} XeService.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Xe;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
public interface XeService {
    public List<Xe> getDsXe();
    public Xe findByID(int id);

    public Xe findByTenXeOrGiaXe(String tenXe);

    public Xe addXe(Xe xe);

    public Xe updateXe(Xe xe);

    public Xe deleteXe(Xe xe);
}

