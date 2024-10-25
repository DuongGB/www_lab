/*
 * @ {#} XeDaoImpl.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.entities.Xe;
import vn.edu.iuh.fit.repositories.XeDao;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
public class XeDaoImpl implements XeDao {
    private EntityManager em;

    public XeDaoImpl() {
        this.em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<Xe> getDsXe() {
        return this.em.createNamedQuery("Xe.findAll", Xe.class).getResultList();
    }

    @Override
    public Xe findByID(int id) {
        return this.em.find(Xe.class, id);
    }

    @Override
    public Xe findByTenXeOrGiaXe(String tenXe) {
        return this.em.createNamedQuery("Xe.findByTenxeIgnoreCaseAndGiaxe", Xe.class)
                .setParameter("tenxe", tenXe)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Xe addXe(Xe xe) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(xe);
            this.em.getTransaction().commit();
            return xe;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Xe updateXe(Xe xe) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(xe);
            this.em.getTransaction().commit();
            return xe;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Xe deleteXe(Xe xe) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(xe);
            this.em.getTransaction().commit();
            return xe;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            return null;
        }
    }
}

