package vn.edu.iuh.fit.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id", nullable = false)
    private Long id;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "cust_dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate custDob;

    @Column(name = "cust_email")
    private String custEmail;

    @Column(name = "cust_name")
    private String custName;

    @OneToMany(mappedBy = "cust")
    @JsonIgnore
    private Set<Account> accounts = new LinkedHashSet<>();

}