/*
 * @ {#} ProductBeanRemote.java   1.0     10/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.business;

import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/10/2024
 * @version:    1.0
 */
public interface ProductBeanRemote {
    void add(Product product);

    List<Product> getAll();

    Product getById(int id);
}
