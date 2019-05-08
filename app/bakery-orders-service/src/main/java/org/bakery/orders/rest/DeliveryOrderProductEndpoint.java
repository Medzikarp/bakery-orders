package org.bakery.orders.rest;

import org.bakery.orders.entity.DeliveryOrderProduct;
import org.bakery.orders.model.DeliveryOrderProducts;
import org.bakery.orders.service.DeliveryOrderProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas Kotol on 17.04.2019.
 */

@Path("/orderProduct")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryOrderProductEndpoint {

    @Inject
    private DeliveryOrderProductService deliveryOrderProductService;


    @GET
    @Path("/order/{id}")
    public Response getOrderProductsByOrder(@PathParam("id") Long id) {
        List<DeliveryOrderProduct> deliveryOrderProducts;
        if (id == null) {
            deliveryOrderProducts = deliveryOrderProductService.findAll();
        } else {
            deliveryOrderProducts = deliveryOrderProductService.searchByDeliveryOrder(id);
        }
        return Response.ok(deliveryOrderProducts).build();
    }

    @GET
    @Path("/product/{id}")
    public Response getOrderProductsByProduct(@PathParam("id") Long id) {
        List<DeliveryOrderProduct> deliveryOrderProducts;
        if (id == null) {
            deliveryOrderProducts = deliveryOrderProductService.findAll();
        } else {
            deliveryOrderProducts = deliveryOrderProductService.searchByProduct(id);
        }
        return Response.ok(deliveryOrderProducts).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        DeliveryOrderProduct deliveryOrderProduct = deliveryOrderProductService.findById(id);
        return Response.ok(deliveryOrderProduct).build();
    }

    @POST
    @Path("/add")
    public Response add(DeliveryOrderProduct deliveryOrderProduct) {
        try {
            DeliveryOrderProduct added = deliveryOrderProductService.associate(deliveryOrderProduct.getDeliveryOrder().getId(), deliveryOrderProduct.getProduct().getId(), deliveryOrderProduct.getQuantity());
            return Response.ok(added).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("/addMultiple")
    public Response add(DeliveryOrderProducts deliveryOrderProducts) {
        List<DeliveryOrderProduct> created = new LinkedList<>();

        // find preset DeliveryOrdersProducts
        List<DeliveryOrderProduct> toDelete = deliveryOrderProductService.searchByDeliveryOrder(deliveryOrderProducts.getDeliveryOrderProducts().get(0).getDeliveryOrder().getId());

        // delete preset DeliveryOrdersProducts
        toDelete.forEach(deliveryOrderProduct -> deliveryOrderProductService.remove(deliveryOrderProduct));

        try {
            deliveryOrderProducts.getDeliveryOrderProducts().forEach(deliveryOrderProduct -> created.add(deliveryOrderProductService.associate(deliveryOrderProduct.getDeliveryOrder().getId(), deliveryOrderProduct.getProduct().getId(), deliveryOrderProduct.getQuantity())));
            return Response.ok(created).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeDeliveryOrderProduct(@PathParam("id") Long id) {
        Response.ResponseBuilder builder;
        DeliveryOrderProduct deliveryOrderProduct = deliveryOrderProductService.findById(id);
        try {
            deliveryOrderProductService.remove(deliveryOrderProduct);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDeliveryOrderProduct(@PathParam("id") Long id, DeliveryOrderProduct deliveryOrderProduct) {
        Response.ResponseBuilder builder;
        DeliveryOrderProduct oldDeliveryOrder = deliveryOrderProductService.findById(id);
        deliveryOrderProduct.setId(oldDeliveryOrder.getId());
        try {
            DeliveryOrderProduct updated = deliveryOrderProductService.update(deliveryOrderProduct);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

}
