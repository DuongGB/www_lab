package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product_image")
public class ProductImage {
    @Id
    @Column(name = "image_id", columnDefinition = "BIGINT(20)")
    private long image_id;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "path", columnDefinition = "VARCHAR(250)")
    private String path;
    @Column(name = "alternative", columnDefinition = "VARCHAR(250)")
    private String alternative;

    public ProductImage(long image_id, Product product, String path, String alternative) {
        this.image_id = image_id;
        this.product = product;
        this.path = path;
        this.alternative = alternative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImage that = (ProductImage) o;
        return image_id == that.image_id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(image_id);
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "image_id=" + image_id +
                ", product=" + product +
                ", path='" + path + '\'' +
                ", alternative='" + alternative + '\'' +
                '}';
    }

}