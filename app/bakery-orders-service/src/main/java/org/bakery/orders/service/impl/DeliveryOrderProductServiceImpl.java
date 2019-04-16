package org.bakery.orders.service.impl;

import org.bakery.orders.entity.DeliveryOrderProduct;
import org.bakery.orders.service.DeliveryOrderProductService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public class DeliveryOrderProductServiceImpl implements DeliveryOrderProductService {

    @Override
    public DeliveryOrderProduct create(DeliveryOrderProduct entity) {
        return null;
    }

    @Override
    public DeliveryOrderProduct update(DeliveryOrderProduct entity) {
        return null;
    }

    @Override
    public void remove(DeliveryOrderProduct entity) {

    }

    @Override
    public DeliveryOrderProduct findById(Long id) {
        return null;
    }

    @Override
    public List<DeliveryOrderProduct> findAll() {
        return null;
    }

    @Override
    public void removeAll() {

    }

    @Override
    public void associate(@NotNull Long deliveryOrderId, @NotNull Long productId) {

    }
}
