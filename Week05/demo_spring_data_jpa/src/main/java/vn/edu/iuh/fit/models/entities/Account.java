/*
 * @ {#} Account.java   1.0     10/19/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/19/2024
 * @version:    1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "account")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, length = 64, nullable = false)
    private String numberAccount;
    private String ownerName;
    private String password;
    private String email;
    private double balance;

    public Account(String ownerName, String email) {
        numberAccount = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.email = email;
        balance = 0;
    }

    public Account(String ownerName, double balance) {
        numberAccount = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.balance = balance;
    }
}

