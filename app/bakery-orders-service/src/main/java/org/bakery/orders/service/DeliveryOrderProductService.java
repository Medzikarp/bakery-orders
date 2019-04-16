package org.bakery.orders.service;

import org.bakery.orders.entity.DeliveryOrderProduct;

import javax.validation.constraints.NotNull;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public interface DeliveryOrderProductService extends GenericCRUDService<DeliveryOrderProduct, Long> {
    void associate(@NotNull Long deliveryOrderId, @NotNull Long productId);
}
