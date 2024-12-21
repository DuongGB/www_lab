/*
 * @ {#} Account.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.jpasecurity_customer.backend.enums.AccountStatus;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private long id;
    @Column(name = "acc_number")
    private double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}

