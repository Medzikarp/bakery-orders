package org.bakery.orders.dao;

import org.bakery.orders.entity.DeliveryOrderProduct;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public interface DeliveryOrderProductDao extends GenericDao<DeliveryOrderProduct, Long> {
    public List<DeliveryOrderProduct> searchByDeliveryOrder(@NotNull Long id);
    public List<DeliveryOrderProduct> searchByProduct(@NotNull Long id);
}
