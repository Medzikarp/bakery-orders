package org.bakery.orders.service.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.DeliveryOrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */

@ApplicationScoped
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Inject
    private UserDao userDao;

    @Override
    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        DeliveryOrder created = deliveryOrderDao.create(deliveryOrder);
        return created;
    }

    @Override
    public DeliveryOrder update(DeliveryOrder deliveryOrder) {
        DeliveryOrder updated = deliveryOrderDao.update(deliveryOrder);
        return updated;
    }

    @Override
    public void remove(DeliveryOrder deliveryOrder) {
        deliveryOrderDao.remove(deliveryOrder.getId());
    }

    @Override
    public DeliveryOrder findById(Long id) {
        return deliveryOrderDao.find(id);
    }

    @Override
    public List<DeliveryOrder> findAll() {
        return deliveryOrderDao.findAll();
    }

    @Override
    public void removeAll() {
        deliveryOrderDao.removeAll();
    }

    @Override
    public List<DeliveryOrder> searchByUser(User user) {
        return deliveryOrderDao.searchByUser(user);
    }

    @Override
    public void addUserToDeliveryOrder(@NotNull Long deliveryOrderId, @NotNull Long userId) {
        DeliveryOrder deliveryOrder = findById(deliveryOrderId);
        User user = userDao.find(userId);
        deliveryOrder.setUser(user);
        deliveryOrderDao.update(deliveryOrder);
    }

}
