/*
 * @ {#} Product.java   1.0     11/2/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.enums.ProductStatus;

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
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id", columnDefinition = "BIGINT(20)")
    private long product_id;
    @Column(name = "name", columnDefinition = "VARCHAR(150)")
    private String name;
    @Column(name = "description", columnDefinition = "VARCHAR(250)")
    private String description;
    @Column(name = "manufacturer_name", columnDefinition = "VARCHAR(100)")
    private String manufacturer;
    @Column(name = "unit", columnDefinition = "VARCHAR(25)")
    private String unit;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "INT(11)")
    private ProductStatus status;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImageList;
    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices;

    public Product(long product_id, String name, String description, String manufacturer, String unit, ProductStatus status) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_id == product.product_id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product_id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unit='" + unit + '\'' +
                ", status=" + status +
                '}';
    }
}
