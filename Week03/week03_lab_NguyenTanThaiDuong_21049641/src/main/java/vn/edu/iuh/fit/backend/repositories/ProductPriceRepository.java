/*
 * @ {#} ProductPriceRepository.java   1.0     10/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.Product;
import vn.edu.iuh.fit.backend.repositories.entities.ProductPrice;

import java.lang.management.ManagementFactory;
import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/10/2024
 * @version:    1.0
 */
// TODO: Add the necessary annotations to make this class a JPA repository
public class ProductPriceRepository {
    @PersistenceContext(unitName = "mariadb")
    private EntityManager em;

    public void add(ProductPrice productPrice) {
        em.persist(productPrice);
    }

    public List<ProductPrice> getAll() {
        return em.createQuery("SELECT p FROM ProductPrice p", ProductPrice.class).getResultList();
    }
    public ProductPrice getById(int id) {
        return em.find(ProductPrice.class, id);
    }
    public ProductPrice findActivePriceByProductId(int productId) {
        return em.createQuery("SELECT p FROM ProductPrice p WHERE p.product.id = :productId AND p.active = true", ProductPrice.class)
                .setParameter("productId", productId)
                .getSingleResult();
    }
}

