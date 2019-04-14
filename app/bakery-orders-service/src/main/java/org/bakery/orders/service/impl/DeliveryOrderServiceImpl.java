package org.bakery.orders.service.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.DeliveryOrderService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */

@ApplicationScoped
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private static final Logger LOGGER = Logger.getLogger(DeliveryOrderServiceImpl.class.getName());

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Inject
    private UserDao userDao;

    @Override
    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        DeliveryOrder created = deliveryOrderDao.create(deliveryOrder);
        LOGGER.info("DeliveryOrder created with id " + deliveryOrder.getId());
        return created;
    }

    @Override
    public DeliveryOrder update(DeliveryOrder deliveryOrder) {
        LOGGER.info("Updating DeliveryOrder with id " + deliveryOrder.getId());
        DeliveryOrder updated = deliveryOrderDao.update(deliveryOrder);
        return updated;
    }

    @Override
    public void remove(DeliveryOrder deliveryOrder) {
        LOGGER.info("Removing DeliveryOrder with id " + deliveryOrder.getId());
        deliveryOrderDao.remove(deliveryOrder.getId());
    }

    @Override
    public DeliveryOrder findById(Long id) {
        LOGGER.info("Searching for DeliveryOrder with id " + id);
        return deliveryOrderDao.find(id);
    }

    @Override
    public List<DeliveryOrder> findAll() {
        LOGGER.info("Searching for all DeliveryOrders");
        return deliveryOrderDao.findAll();
    }

    @Override
    public void removeAll() {
        LOGGER.info("Removing all DeliveryOrders");
        deliveryOrderDao.removeAll();
    }

    @Override
    public List<DeliveryOrder> searchByUser(User user) {
        LOGGER.info("Searching for all DeliveryOrders with user id " + user.getId());
        return deliveryOrderDao.searchByUser(user);
    }

    @Override
    public void addUserToDeliveryOrder(@NotNull Long deliveryOrderId, @NotNull Long userId) {
        LOGGER.info("Adding user " + userId + " to DeliveryOrder " + deliveryOrderId);
        DeliveryOrder deliveryOrder = findById(deliveryOrderId);
        User user = userDao.find(userId);
        deliveryOrder.setUser(user);
        deliveryOrderDao.update(deliveryOrder);
    }

}
