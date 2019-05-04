package org.bakery.orders.builder;

import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.State;
import org.bakery.orders.entity.User;

import java.time.LocalDateTime;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */
public final class DeliveryOrderBuilder {
    private String name;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private State state;

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

    public DeliveryOrderBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DeliveryOrderBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public DeliveryOrderBuilder withState(State state) {
        this.state = state;
        return this;
    }

    public DeliveryOrder build() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName(name);
        deliveryOrder.setUser(user);
        deliveryOrder.setCreatedAt(createdAt);
        deliveryOrder.setUpdatedAt(updatedAt);
        deliveryOrder.setState(state);
        return deliveryOrder;
    }
}
