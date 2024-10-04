/*
 * @ {#} Controllers.java   1.0     10/4/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/4/2024
 * @version:    1.0
 */
// Đánh dấu đây là một controller servlet để xử lý các request từ client gửi lên
@WebServlet(name = "Controllers", urlPatterns = {"/controller"})
public class Controllers extends HttpServlet {

}

