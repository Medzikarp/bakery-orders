package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.ProductDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@ApplicationScoped
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> searchByCategory(@NotNull Long id) {
        TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId", Product.class)
                .setParameter("categoryId", id);
        return q.getResultList();
    }
}
