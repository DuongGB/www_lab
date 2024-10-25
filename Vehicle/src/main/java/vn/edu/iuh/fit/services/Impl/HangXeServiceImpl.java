/*
 * @ {#} HangXeServiceImpl.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services.Impl;

import vn.edu.iuh.fit.entities.HangXe;
import vn.edu.iuh.fit.repositories.HangXeDao;
import vn.edu.iuh.fit.repositories.Impl.HangXeDaoImpl;
import vn.edu.iuh.fit.services.HangXeService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
public class HangXeServiceImpl implements HangXeService {
    private HangXeDao hangXeDao;

    public HangXeServiceImpl() {
        this.hangXeDao = new HangXeDaoImpl();
    }

    @Override
    public List<HangXe> getDsHangXe() {
        return hangXeDao.getDsHangXe();
    }

    @Override
    public HangXe findByID(int id) {
        return hangXeDao.findByID(id);
    }
}

