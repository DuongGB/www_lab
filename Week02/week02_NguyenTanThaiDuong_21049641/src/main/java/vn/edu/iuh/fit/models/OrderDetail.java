/*
 * @ {#} OrderDetail.java   1.0     11/2/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity", columnDefinition = "DOUBLE")
    private double  quantity;
    @Column(name = "price", columnDefinition = "DOUBLE")
    private double price;
    @Column(name = "note", columnDefinition = "VARCHAR(255)")
    private String note;

    public OrderDetail(Order order, Product product, double quantity, double price, String note) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", note='" + note + '\'' +
                '}';
    }
}

