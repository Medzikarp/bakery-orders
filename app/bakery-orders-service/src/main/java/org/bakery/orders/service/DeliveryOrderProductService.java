package org.bakery.orders.service;

import org.bakery.orders.entity.DeliveryOrderProduct;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public interface DeliveryOrderProductService extends GenericCRUDService<DeliveryOrderProduct, Long> {
    DeliveryOrderProduct associate(@NotNull Long deliveryOrderId, @NotNull Long productId, @NotNull Long quantity);
    List<DeliveryOrderProduct> searchByDeliveryOrder(@NotNull Long id);
    List<DeliveryOrderProduct> searchByProduct(@NotNull Long id);
}
