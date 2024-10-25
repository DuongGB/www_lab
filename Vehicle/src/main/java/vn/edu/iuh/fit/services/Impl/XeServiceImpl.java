/*
 * @ {#} XeServiceImpl.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services.Impl;

import vn.edu.iuh.fit.entities.Xe;
import vn.edu.iuh.fit.repositories.Impl.XeDaoImpl;
import vn.edu.iuh.fit.repositories.XeDao;
import vn.edu.iuh.fit.services.XeService;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
public class XeServiceImpl implements XeService {
    private XeDao xeDao;

    public XeServiceImpl() {
        this.xeDao = new XeDaoImpl();
    }

    @Override
    public List<Xe> getDsXe() {
        return xeDao.getDsXe();
    }

    @Override
    public Xe findByID(int id) {
        return xeDao.findByID(id);
    }

    @Override
    public Xe findByTenXeOrGiaXe(String tenXe) {
        return xeDao.findByTenXeOrGiaXe(tenXe);
    }

    @Override
    public Xe addXe(Xe xe) {
        return xeDao.addXe(xe);
    }

    @Override
    public Xe updateXe(Xe xe) {
        return xeDao.updateXe(xe);
    }

    @Override
    public Xe deleteXe(Xe xe) {
        return xeDao.deleteXe(xe);
    }
}

