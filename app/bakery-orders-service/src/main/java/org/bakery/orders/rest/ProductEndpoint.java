package org.bakery.orders.rest;

import org.bakery.orders.entity.Product;
import org.bakery.orders.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductEndpoint {

    @Inject
    private ProductService productService;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Product product = productService.findById(id);
        return Response.ok(product).build();
    }

    @GET
    public Response getProducts() {
        List<Product> products = productService.findAll();
        return Response.ok(products).build();
    }

    @POST
    public Response createProduct(Product product) {
        Response.ResponseBuilder builder;
        try {
            Product created = productService.create(product);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, Product product) {
        Response.ResponseBuilder builder;
        Product oldProduct = productService.findById(id);
        product.setId(oldProduct.getId());
        try {
            Product updated = productService.update(product);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    public Response removeProduct(@PathParam("id") Long id) {
        Response.ResponseBuilder builder;
        Product product = productService.findById(id);
        try {
            productService.remove(product);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

}
