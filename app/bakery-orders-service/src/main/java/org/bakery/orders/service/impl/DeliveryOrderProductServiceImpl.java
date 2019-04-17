package org.bakery.orders.service.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.DeliveryOrderProductDao;
import org.bakery.orders.dao.ProductDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.DeliveryOrderProduct;
import org.bakery.orders.entity.Product;
import org.bakery.orders.service.DeliveryOrderProductService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */

@ApplicationScoped
public class DeliveryOrderProductServiceImpl implements DeliveryOrderProductService {

    private static final Logger LOGGER = Logger.getLogger(DeliveryOrderProductServiceImpl.class.getName());

    @Inject
    private DeliveryOrderProductDao deliveryOrderProductDao;

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Inject
    private ProductDao productDao;

    @Override
    public DeliveryOrderProduct create(DeliveryOrderProduct deliveryOrderProduct) {
        DeliveryOrderProduct created = deliveryOrderProductDao.create(deliveryOrderProduct);
        LOGGER.info("DeliveryOrderProduct created with id " + created.getId());
        return created;
    }

    @Override
    public DeliveryOrderProduct update(DeliveryOrderProduct deliveryOrderProduct) {
        DeliveryOrderProduct updated = deliveryOrderProductDao.update(deliveryOrderProduct);
        LOGGER.info("DeliveryOrderProduct updated with id " + updated.getId());
        return updated;
    }

    @Override
    public void remove(DeliveryOrderProduct deliveryOrderProduct) {
        LOGGER.info("Removing DeliveryOrderProduct with id " + deliveryOrderProduct.getId());
        deliveryOrderProductDao.remove(deliveryOrderProduct.getId());
    }

    @Override
    public DeliveryOrderProduct findById(Long id) {
        LOGGER.info("Searching for DeliveryOrderProduct with id " + id);
        return deliveryOrderProductDao.find(id);
    }

    @Override
    public List<DeliveryOrderProduct> findAll() {
        LOGGER.info("Searching for all DeliveryOrderProducts");
        return deliveryOrderProductDao.findAll();
    }

    @Override
    public void removeAll() {
        LOGGER.info("Removing all DeliveryOrderProducts");
        deliveryOrderProductDao.removeAll();
    }

    @Override
    public DeliveryOrderProduct associate(@NotNull Long deliveryOrderId, @NotNull Long productId, @NotNull Long quantity) {
        LOGGER.info("Associating DeliveryOrder " + deliveryOrderId + " with Product " + productId);
        DeliveryOrder deliveryOrder = deliveryOrderDao.find(deliveryOrderId);
        Product product = productDao.find(productId);

        DeliveryOrderProduct deliveryOrderProduct = new DeliveryOrderProduct();
        deliveryOrderProduct.setQuantity(quantity);
        deliveryOrderProduct.setDeliveryOrder(deliveryOrder);
        deliveryOrderProduct.setProduct(product);

        return create(deliveryOrderProduct);
    }

    @Override
    public List<DeliveryOrderProduct> searchByDeliveryOrder(@NotNull Long id) {
        LOGGER.info("Searching for DeliveryOrderProducts by DeliveryOrder with id " + id);
        return deliveryOrderProductDao.searchByDeliveryOrder(id);
    }

    @Override
    public List<DeliveryOrderProduct> searchByProduct(@NotNull Long id) {
        LOGGER.info("Searching for DeliveryOrderProducts by Product with id " + id);
        return deliveryOrderProductDao.searchByProduct(id);
    }


}
