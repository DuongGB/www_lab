/*
 * @ {#} Order.java   1.0     11/2/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id", columnDefinition = "BIGINT(20)")
    private long id;
    @Column(name = "order_date", columnDefinition = "DATETIME(6)")
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "custr_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Order(long id, LocalDateTime orderDate, Customer customer, Employee employee) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", employee=" + employee +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

