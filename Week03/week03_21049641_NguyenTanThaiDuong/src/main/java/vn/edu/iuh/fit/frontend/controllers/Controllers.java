/*
 * @ {#} Controllers.java   1.0     10/4/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.backend.repositories.entities.Product;
import vn.edu.iuh.fit.frontend.models.ProductModel;

import java.io.IOException;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/4/2024
 * @version:    1.0
 */
// Đánh dấu đây là một controller servlet để xử lý các request từ client gửi lên
@WebServlet(name = "Controllers", value = "/controller")
public class Controllers extends HttpServlet {

    @Inject
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("Action: " + action);
        if (action.equals("add")) {
            String name = req.getParameter("product_name");
            String description = req.getParameter("product_description");
            String imgPath = req.getParameter("img_path");
            Product product = new Product(name, description, imgPath);
            productModel.createProduct(product);
            resp.sendRedirect("index.jsp");
        }

    }
}

