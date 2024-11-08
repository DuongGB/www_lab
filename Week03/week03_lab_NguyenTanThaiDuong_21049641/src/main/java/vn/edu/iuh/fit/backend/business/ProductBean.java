/*
 * @ {#} ProductBean.java   1.0     10/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRespository;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/10/2024
 * @version:    1.0
 */
// TODO: Add the necessary annotations to make this class a stateless session bean
@Stateless
public class ProductBean implements ProductBeanRemote {
    @Inject
    private ProductRespository productRespository;
    @Inject
    private ProductPriceRepository productPriceRepository;

    @Override
    public void add(Product product) {
        productRespository.add(product);
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }

    @Override
    public Product getById(int id) {
        return null;
    }
}

