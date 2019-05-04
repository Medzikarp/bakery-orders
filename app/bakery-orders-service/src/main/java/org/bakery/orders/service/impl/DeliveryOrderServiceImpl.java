package org.bakery.orders.service.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.DeliveryOrderProductDao;
import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.DeliveryOrderService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private DeliveryOrderProductDao deliveryOrderProductDao;


    @Override
    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        deliveryOrder.setCreatedAt(LocalDateTime.now());
        deliveryOrder.setUpdatedAt();
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
        findAll().forEach(deliveryOrder -> deliveryOrderProductDao.searchByDeliveryOrder(deliveryOrder.getId()).forEach(deliveryOrderProduct -> {
            deliveryOrderProductDao.remove(deliveryOrderProduct.getId());
        }));
        deliveryOrderDao.removeAll();
    }

    @Override
    public List<DeliveryOrder> searchByUser(Long id) {
        LOGGER.info("Searching for all DeliveryOrders with user id " + id);
        return deliveryOrderDao.searchByUser(id);
    }
}
