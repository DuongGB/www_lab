package vn.edu.iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import vn.edu.iuh.fit.models.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class DemoSpringDataJpaApplication implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringDataJpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        Random rnd = new Random();
////        for (int i = 0; i < 100; i++) {
////            Account account = new Account("Owner " + i, rnd.nextDouble(100000));
////            accountRepository.save(account);
////        }
////        accountRepository.findAll().forEach(System.out::println);
//        PageRequest request = PageRequest.of(0, 5);
////        Page<Account> content = accountRepository.findAll(request);
////        List<Account> accounts = content.getContent();
////        accounts.forEach(System.out::println);
//        List<Account> lst = accountRepository.findByBalanceGreaterThan(70000, request);
//        lst.forEach(System.out::println);
    }
}
