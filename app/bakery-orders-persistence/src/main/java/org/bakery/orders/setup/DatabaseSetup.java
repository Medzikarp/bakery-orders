package org.bakery.orders.setup;

import org.bakery.orders.builder.DeliveryOrderBuilder;
import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;

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
    private DeliveryOrderDao deliveryOrderDao;

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void init() {
        User user1 = UserBuilder.anUser().withName("User 1").withEmail("user@seznam.cz").build();
        userDao.create(user1);

        deliveryOrderDao.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("First 1").build());
        deliveryOrderDao.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("First 2").build());
        deliveryOrderDao.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("First 3").build());


    }

}
