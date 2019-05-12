package org.bakery.orders.service.impl;

import org.bakery.orders.dao.CategoryDao;
import org.bakery.orders.dao.DeliveryOrderProductDao;
import org.bakery.orders.dao.ProductDao;
import org.bakery.orders.entity.Product;
import org.bakery.orders.service.ProductService;
import org.jboss.logging.Logger;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

    @Inject
    private ProductDao productDao;

    @Inject
    private DeliveryOrderProductDao deliveryOrderProductDao;

    @Inject
    private CategoryDao categoryDao;

    @Override
    public Product create(Product product) {
        Product created = productDao.create(product);
        LOGGER.info("Product created with id " + created.getId());
        return created;
    }

    @Override
    @Asynchronous
    public Future<Product> createAsync(Product product) {
        Product created = productDao.create(product);
        LOGGER.info("Product created with id " + created.getId());
        return new AsyncResult<>(created);
    }

    @Override
    public Product update(Product product) {
        Product updated = productDao.update(product);
        LOGGER.info("Updating Product with id " + updated.getId());
        return updated;
    }

    @Override
    public void remove(Product product) {
        LOGGER.info("Removing Product with id " + product.getId());
        deliveryOrderProductDao.searchByProduct(product.getId()).forEach(deliveryOrderProduct -> {
            deliveryOrderProductDao.remove(deliveryOrderProduct.getId());
        });
        productDao.remove(product.getId());
    }

    @Override
    public Product findById(Long id) {
        LOGGER.info("Searching for Product with id " + id);
        return productDao.find(id);
    }

    @Override
    public List<Product> findAll() {
        LOGGER.info("Searching for all Products");
        return productDao.findAll();
    }

    @Override
    public void removeAll() {
        LOGGER.info("Removing all Products");
        findAll().forEach(product -> deliveryOrderProductDao.searchByProduct(product.getId()).forEach(deliveryOrderProduct -> {
            deliveryOrderProductDao.remove(deliveryOrderProduct.getId());
        }));
        productDao.removeAll();
    }

    @Override
    public List<Product> searchByCategory(@NotNull Long id) {
        LOGGER.info("Searching for all Products with categoryId " + id);
        return productDao.searchByCategory(id);
    }
}
