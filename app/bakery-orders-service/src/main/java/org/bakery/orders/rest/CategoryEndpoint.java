package org.bakery.orders.rest;

import org.bakery.orders.entity.Category;
import org.bakery.orders.entity.Product;
import org.bakery.orders.service.CategoryService;
import org.bakery.orders.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryEndpoint {

    @Inject
    private CategoryService categoryService;

    @Inject
    private ProductService productService;

    @GET
    @Path("/product/{id}")
    public Response getCategoriesByProduct(@PathParam("id") Long id) {
        List<Category> categories = new LinkedList<>();
        Product product = productService.findById(id);
        if (product != null) {
            categories = product.getCategories();
        }
        return Response.ok(categories).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Category category = categoryService.findById(id);
        return Response.ok(category).build();
    }

    @GET
    public Response getCategories() {
        List<Category> categories = categoryService.findAll();
        return Response.ok(categories).build();
    }

    @POST
    public Response createCategory(Category category) {
        Response.ResponseBuilder builder;
        try {
            Category created = categoryService.create(category);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") Long id, Category category) {
        Response.ResponseBuilder builder;
        Category oldCategory = categoryService.findById(id);
        category.setId(oldCategory.getId());
        try {
            Category updated = categoryService.update(category);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    public Response removeCategory(@PathParam("id") Long id) {
        Response.ResponseBuilder builder;
        Category category = categoryService.findById(id);
        try {
            categoryService.remove(category);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }
}
