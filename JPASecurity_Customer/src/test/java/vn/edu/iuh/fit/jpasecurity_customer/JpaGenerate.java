/*
 * @ {#} JpaGenerate.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@SpringBootTest
public class JpaGenerate {
    @Test
    public void GenerateJPA() {
        EntityManager em = Persistence.createEntityManagerFactory("JPA_Maria").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}

