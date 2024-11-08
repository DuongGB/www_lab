/*
 * @ {#} ProductModel.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;

import jakarta.json.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.dtos.ProductDto;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/11/2024
 * @version:    1.0
 */
// TODO: Add the necessary annotations to make this class a model class used to interact with the backend
public class ProductModel {
    private final String ADD_URL = "http://localhost:8080/week03_lab_NguyenTanThaiDuong_21049641-1.0-SNAPSHOT/api/products";

    public void createProduct(Product product) {
        // TODO: Send a POST request to the backend to add a new product
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL);
            Response response = target.request().post(Entity.json(product));
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL);
            Response response = target.request().accept("application/json").get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                String jsonResponse = response.readEntity(String.class);
                System.out.println("Response: " + jsonResponse);
                try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
                    JsonArray jsonArray = jsonReader.readArray();
                    for (JsonValue jsonValue : jsonArray) {
                        JsonObject jsonObject = (JsonObject) jsonValue;
                        ProductDto productDto = new ProductDto();
                        productDto.setName(jsonObject.getString("name"));
                        productDto.setDescription(jsonObject.getString("description"));
                        productDto.setImgPath(jsonObject.getString("imgPath"));
                        productDto.setPrice(jsonObject.getJsonNumber("price").doubleValue());
                    }
                }
            } else {
                System.out.println("Failed" + response.getStatus());
            }
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}

