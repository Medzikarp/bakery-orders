package org.bakery.orders.service;

import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */
public interface DeliveryOrderService extends GenericCRUDService<DeliveryOrder, Long> {
    List<DeliveryOrder> searchByUser(User user);
    void addUserToDeliveryOrder(@NotNull Long deliveryOrderId, @NotNull Long userId);
}
