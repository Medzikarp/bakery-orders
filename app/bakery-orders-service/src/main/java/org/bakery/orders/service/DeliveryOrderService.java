package org.bakery.orders.service;

import org.bakery.orders.entity.DeliveryOrder;

import java.util.List;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */
public interface DeliveryOrderService extends GenericCRUDService<DeliveryOrder, Long> {
    List<DeliveryOrder> searchByKeycloakId(String keycloakId);
}
