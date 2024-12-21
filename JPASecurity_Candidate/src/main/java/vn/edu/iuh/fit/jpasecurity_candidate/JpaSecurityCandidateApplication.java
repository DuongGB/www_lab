package vn.edu.iuh.fit.jpasecurity_candidate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaSecurityCandidateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSecurityCandidateApplication.class, args);
    }

//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        EntityManager em = Persistence.createEntityManagerFactory("JPA_Maria").createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        }
//    }
}
