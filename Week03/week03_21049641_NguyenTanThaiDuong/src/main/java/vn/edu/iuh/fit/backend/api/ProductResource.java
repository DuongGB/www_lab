/*
 * @ {#} ProductResource.java   1.0     10/4/2024
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
 * @date:   10/4/2024
 * @version:    1.0
 */
//Dùng để đánh dấu đây là một đối tượng tài nguyên của API RESTful
@Path("/products")
public class ProductResource {
    @EJB
    private ProductBeanRemote productBeanRemote;

    @GET
    public Response getAll() {
        return Response.status(Response.Status.OK).entity(productBeanRemote.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(productBeanRemote.getById(id)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response add(Product product) {
        productBeanRemote.add(product);
        return Response.ok(product).build();
    }

}

