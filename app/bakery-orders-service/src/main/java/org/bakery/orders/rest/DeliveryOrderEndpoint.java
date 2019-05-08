package org.bakery.orders.rest;


import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.service.DeliveryOrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryOrderEndpoint {

    @Inject
    private DeliveryOrderService deliveryOrderService;

    @GET
    @Path("/user/{id}")
    public Response getOrdersByUser(@PathParam("id") Long id) {
        List<DeliveryOrder> deliveryOrders;
        if (id == null) {
            deliveryOrders = deliveryOrderService.findAll();
        } else {
            deliveryOrders = deliveryOrderService.searchByUser(id);
        }
        return Response.ok(deliveryOrders).build();
    }

    @GET
    public Response getOrders() {
        List<DeliveryOrder> deliveryOrders = deliveryOrderService.findAll();
        return Response.ok(deliveryOrders).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderService.findById(id);
        return Response.ok(deliveryOrder).build();
    }

    @POST
    public Response createOrder(DeliveryOrder deliveryOrder) {
        Response.ResponseBuilder builder;
        try {
            DeliveryOrder created = deliveryOrderService.create(deliveryOrder);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") Long id, DeliveryOrder deliveryOrder) {
        Response.ResponseBuilder builder;
        DeliveryOrder oldDeliveryOrder = deliveryOrderService.findById(id);
        deliveryOrder.setId(oldDeliveryOrder.getId());
        deliveryOrder.setCreatedAt(oldDeliveryOrder.getCreatedAt());
        deliveryOrder.setState(oldDeliveryOrder.getState());
        try {
            DeliveryOrder updated = deliveryOrderService.update(deliveryOrder);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    public Response removeOrder(@PathParam("id") Long id) {
        Response.ResponseBuilder builder;
        DeliveryOrder deliveryOrder = deliveryOrderService.findById(id);
        try {
            deliveryOrderService.remove(deliveryOrder);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

}
