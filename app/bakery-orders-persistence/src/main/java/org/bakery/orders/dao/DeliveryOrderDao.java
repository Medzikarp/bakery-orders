package org.bakery.orders.dao;
import org.bakery.orders.entity.DeliveryOrder;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */
public interface DeliveryOrderDao {
    void save(DeliveryOrder deliveryOrder);

    Optional<DeliveryOrder> get(long id);

    List<DeliveryOrder> getAll();


    void update(DeliveryOrder deliveryOrder);

    void delete(DeliveryOrder deliveryOrder);

    void deleteAll();
}
