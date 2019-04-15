package org.bakery.orders.setup;

import org.bakery.orders.builder.DeliveryOrderBuilder;
import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.DeliveryOrderService;
import org.bakery.orders.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;

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

    @PostConstruct
    public void init() {
        User user1 = UserBuilder.anUser().withName("User 1").withEmail("user@seznam.cz").build();
        userService.create(user1);

        deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("First 1").build());
        deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("First 2").build());
        deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("First 3").build());


    }

}
