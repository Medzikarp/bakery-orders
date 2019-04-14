package org.bakery.orders.setup;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.entity.DeliveryOrder;

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

    @PostConstruct
    public void init() {
        deliveryOrderDao.create(new DeliveryOrder());
        deliveryOrderDao.create(new DeliveryOrder());
        deliveryOrderDao.create(new DeliveryOrder());
    }

}
