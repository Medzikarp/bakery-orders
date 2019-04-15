package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.ProductDao;
import org.bakery.orders.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@ApplicationScoped
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }
}
