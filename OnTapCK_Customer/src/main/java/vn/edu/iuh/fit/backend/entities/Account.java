package vn.edu.iuh.fit.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import vn.edu.iuh.fit.backend.converters.AccountConverter;
import vn.edu.iuh.fit.backend.enums.AccountStatus;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id", nullable = false)
    private Long id;

    @Column(name = "acc_number")
    private Double accNumber;

    @Column(name = "status")
//    @Convert(converter = AccountConverter.class)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer cust;

}