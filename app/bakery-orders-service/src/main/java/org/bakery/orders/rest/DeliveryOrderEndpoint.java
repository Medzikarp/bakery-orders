package org.bakery.orders.rest;


import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.DeliveryOrderService;
import org.bakery.orders.service.UserService;
import org.jboss.logging.Logger;
import org.keycloak.representations.AccessToken;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryOrderEndpoint {

    private static final Logger LOGGER = Logger.getLogger(DeliveryOrderEndpoint.class.getName());


    @Inject
    private DeliveryOrderService deliveryOrderService;

    @Inject
    private UserService userService;

    @Inject
    private AccessToken accessToken;

    @GET
    public Response getOrders() {
        List<DeliveryOrder> deliveryOrders;
        deliveryOrders = deliveryOrderService.searchByKeycloakId(accessToken.getId());
        return Response.ok(deliveryOrders).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderService.findById(id);
        if (deliveryOrder.getUser().getKeycloakId() != accessToken.getId()) {
            Response.status(Response.Status.UNAUTHORIZED);
        }
        return Response.ok(deliveryOrder).build();
    }

    @POST
    public Response createOrder(DeliveryOrder deliveryOrder) {
        Response.ResponseBuilder builder;
        LOGGER.info("Creating order for userId: " + accessToken.getId() + " with name: " + accessToken.getName() + " and email: " + accessToken.getEmail());
        User savedUser = userService.findByKeycloakId(accessToken.getId());
        if (savedUser == null) {
            try {
                savedUser = userService.create(
                        UserBuilder.anUser()
                                .withKeycloakId(accessToken.getId())
                                .withName(accessToken.getName())
                                .withEmail(accessToken.getEmail())
                                .build());
            } catch (Exception e) {
                builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage());
                return builder.build();
            }
        }
        try {
            deliveryOrder.setUser(savedUser);
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
        if (oldDeliveryOrder.getUser().getKeycloakId() != accessToken.getId()) {
            Response.status(Response.Status.UNAUTHORIZED);
        }
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
        if (deliveryOrder.getUser().getKeycloakId() != accessToken.getId()) {
            Response.status(Response.Status.UNAUTHORIZED);
        }
        try {
            deliveryOrderService.remove(deliveryOrder);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

}
