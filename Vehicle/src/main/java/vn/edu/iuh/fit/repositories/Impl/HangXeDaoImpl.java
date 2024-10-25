/*
 * @ {#} HangXeDaoImpl.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.entities.HangXe;
import vn.edu.iuh.fit.repositories.HangXeDao;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
public class HangXeDaoImpl implements HangXeDao {
    private EntityManager em;

    public HangXeDaoImpl() {
        this.em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<HangXe> getDsHangXe() {
        return this.em.createNamedQuery("HangXe.findAll", HangXe.class).getResultList();
    }

    @Override
    public HangXe findByID(int id) {
        return this.em.createNamedQuery("HangXe.findById", HangXe.class).setParameter("id", id).getSingleResult();
    }
}

