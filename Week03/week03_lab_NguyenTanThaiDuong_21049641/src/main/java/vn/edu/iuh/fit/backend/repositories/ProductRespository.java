/*
 * @ {#} ProductRespository.java   1.0     10/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/10/2024
 * @version:    1.0
 */
// TODO: Add the necessary annotations to make this class a JPA repository
public class ProductRespository {
    @PersistenceContext(unitName = "mariadb")
    private EntityManager em;

    public void add(Product product) {
        em.persist(product);
    }

    public List<Product> getAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Product getById(int id) {
        return em.find(Product.class, id);
    }
}

