package org.bakery.orders.builder;

import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */
public final class DeliveryOrderBuilder {
    private String name;
    private User user;

    private DeliveryOrderBuilder() {
    }

    public static DeliveryOrderBuilder aDeliveryOrder() {
        return new DeliveryOrderBuilder();
    }

    public DeliveryOrderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DeliveryOrderBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public DeliveryOrder build() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName(name);
        deliveryOrder.setUser(user);
        return deliveryOrder;
    }
}
