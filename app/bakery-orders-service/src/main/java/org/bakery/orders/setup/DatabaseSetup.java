package org.bakery.orders.setup;

import org.bakery.orders.builder.DeliveryOrderBuilder;
import org.bakery.orders.builder.ProductBuilder;
import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.Product;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.DeliveryOrderProductService;
import org.bakery.orders.service.DeliveryOrderService;
import org.bakery.orders.service.ProductService;
import org.bakery.orders.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by Lukas Kotol on 14.04.2019.
 */

@Singleton
@Startup
public class DatabaseSetup {

    @Inject
    private DeliveryOrderService deliveryOrderService;

    @Inject
    private UserService userService;

    @Inject
    private DeliveryOrderProductService deliveryOrderProductService;

    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        User user1 = UserBuilder.anUser().withName("User 1").withEmail("user@seznam.cz").build();
        userService.create(user1);

        DeliveryOrder order1 = deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("DeliveryOrder 1").build());
        DeliveryOrder order2 = deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("DeliveryOrder 2").build());
        DeliveryOrder order3 = deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("DeliveryOrder 3").build());

        Product product1 = productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withDescription("Description 1").withName("Product 1").build());
        Product product2 = productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withDescription("Description 2").withName("Product 2").build());
        Product product3 = productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withDescription("Description 3").withName("Product 3").build());

        deliveryOrderProductService.associate(order1.getId(), product1.getId(), 2L);
        deliveryOrderProductService.associate(order1.getId(), product2.getId(), 2L);
        deliveryOrderProductService.associate(order2.getId(), product2.getId(), 2L);
        deliveryOrderProductService.associate(order3.getId(), product3.getId(), 2L);
    }

}
