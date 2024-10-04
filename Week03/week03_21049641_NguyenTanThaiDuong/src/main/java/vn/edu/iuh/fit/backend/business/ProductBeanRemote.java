/*
 * @ {#} ProductBeanRemote.java   1.0     10/4/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/4/2024
 * @version:    1.0
 */
// Đánh dấu đây là một interface remote
@Remote
public interface ProductBeanRemote {
    List<Product> getAll();

    void add(Product product);

    Product getById(int id);
}

