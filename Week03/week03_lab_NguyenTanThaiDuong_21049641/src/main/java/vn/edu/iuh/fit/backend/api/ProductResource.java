/*
 * @ {#} ProductResource.java   1.0     10/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.api;

import jakarta.ejb.EJB;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.business.ProductBeanRemote;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/10/2024
 * @version:    1.0
 */
// TODO: Add the necessary annotations to make this class a RESTful web service
@Path("/products")
public class ProductResource {
    @EJB
    private ProductBeanRemote productBean;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(productBean.getAll()).build();
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(int id) {
        return Response.ok(productBean.getById(id)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response add(Product product) {
        productBean.add(product);
        return Response.ok().build();
    }
}

