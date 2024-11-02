/*
 * @ {#} Customer.java   1.0     11/2/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/2/2024
 * @version:    1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cust_id", columnDefinition = "BIGINT(20)")
    private long id;
    @Column(name = "cust_name", columnDefinition = "VARCHAR(150)")
    private String name;
    @Column(name = "email", columnDefinition = "VARCHAR(150)")
    private String email;
    @Column(name = "address", columnDefinition = "VARCHAR(250)")
    private String address;
    @Column(name = "phone", columnDefinition = "VARCHAR(15)")
    private String phone;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orderList;

    public Customer(long id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

