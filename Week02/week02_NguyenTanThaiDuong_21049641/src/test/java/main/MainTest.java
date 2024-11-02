/*
 * @ {#} MainTest.java   1.0     11/2/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/2/2024
 * @version:    1.0
 */
public class MainTest {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("mariadb");
        EntityManager em= emf.createEntityManager();
        EntityTransaction tx= em.getTransaction();
        try {
            tx.begin();
            // code here
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}

