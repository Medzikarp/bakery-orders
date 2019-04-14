package org.bakery.orders.rest;


import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.service.DeliveryOrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryOrderEndpoint {

    @Inject
    private DeliveryOrderService deliveryOrderService;

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderService.findById(id);
        return Response.ok(deliveryOrder).build();
    }

}
