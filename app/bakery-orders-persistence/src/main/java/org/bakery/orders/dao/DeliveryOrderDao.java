package org.bakery.orders.dao;

import org.bakery.orders.entity.DeliveryOrder;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */
public interface DeliveryOrderDao extends GenericDao<DeliveryOrder, Long> {
    List<DeliveryOrder> searchByKeycloakId(@NotNull String id);

}
