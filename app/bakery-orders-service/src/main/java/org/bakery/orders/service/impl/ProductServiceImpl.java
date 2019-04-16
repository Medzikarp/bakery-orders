package org.bakery.orders.service.impl;

import org.bakery.orders.dao.ProductDao;
import org.bakery.orders.entity.Product;
import org.bakery.orders.service.ProductService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        Product created = productDao.create(product);
        LOGGER.info("Product created with id " + created.getId());
        return created;
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
        productDao.removeAll();
    }
}
