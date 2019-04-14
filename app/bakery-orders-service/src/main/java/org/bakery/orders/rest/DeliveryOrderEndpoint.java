package org.bakery.orders.rest;


import org.bakery.orders.service.DeliveryOrderService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/orders")
public class DeliveryOrderEndpoint {

    @Inject
    private DeliveryOrderService deliveryOrderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder() {
        return Response.ok("test").build();
    }

}
