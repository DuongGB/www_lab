/*
 * @ {#} ProductModel.java   1.0     10/4/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/4/2024
 * @version:    1.0
 */
// Đánh dấu đây là một model dùng để chứa dữ liệu của sản phẩm
public class ProductModel {
    private final String ADD_URL = "http://localhost:8080/week03_21049641_NguyenTanThaiDuong-1.0-SNAPSHOT/api/products";

    public void createProduct(Product product) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL);

            Response response = target.request().post(Entity.json(product));
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Failed to add product");
            }
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

