/*
 * @ {#} ProductBean.java   1.0     10/4/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/4/2024
 * @version:    1.0
 */
@Stateless
public class ProductBean implements ProductBeanRemote {

    @PersistenceContext(unitName = "week3_21049641")
    private EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        return entityManager.createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public void add(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product getById(int id) {
        return entityManager.createNamedQuery("Product.findById", Product.class).setParameter("id", id).getSingleResult();
    }
}

