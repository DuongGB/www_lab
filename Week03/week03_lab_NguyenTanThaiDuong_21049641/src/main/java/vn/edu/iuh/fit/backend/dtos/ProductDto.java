package vn.edu.iuh.fit.backend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * DTO for {@link vn.edu.iuh.fit.backend.repositories.entities.Product}
 */
// TODO: Add the necessary annotations to make this class a DTO used to transfer data between the frontend and the backend
public class ProductDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 150)
    private String name;
    @NotNull
    private String description;
    @Size(max = 250)
    private String imgPath;
    private double price;

    public ProductDto() {
    }

    public ProductDto(Integer id, String name, String description, String imgPath, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public ProductDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgPath() {
        return imgPath;
    }

    public ProductDto setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto entity = (ProductDto) o;
        return Objects.equals(this.id, entity.id) && Objects.equals(this.name, entity.name) && Objects.equals(this.description, entity.description) && Objects.equals(this.imgPath, entity.imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imgPath);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + id + ", " + "name = " + name + ", " + "description = " + description + ", " + "imgPath = " + imgPath + ")";
    }

    public String getFormattedPrice() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(price);
    }
}