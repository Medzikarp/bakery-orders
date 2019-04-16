package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.DeliveryOrderProductDao;
import org.bakery.orders.entity.DeliveryOrderProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */

@ApplicationScoped
@Transactional
public class DeliveryOrderProductDaoImpl extends GenericDaoImpl<DeliveryOrderProduct, Long> implements DeliveryOrderProductDao {

    public DeliveryOrderProductDaoImpl() {
        super(DeliveryOrderProduct.class);
    }

    @Override
    public List<DeliveryOrderProduct> searchByDeliveryOrder(@NotNull Long id) {
        TypedQuery<DeliveryOrderProduct> q = em.createQuery("SELECT d FROM DeliveryOrderProduct d WHERE d.deliveryOrder.id = :deliveryOrderId", DeliveryOrderProduct.class)
                .setParameter("deliveryOrderId", id);
        return q.getResultList();
    }

    @Override
    public List<DeliveryOrderProduct> searchByProduct(@NotNull Long id) {
        TypedQuery<DeliveryOrderProduct> q = em.createQuery("SELECT d FROM DeliveryOrderProduct d WHERE d.product.id = :productId", DeliveryOrderProduct.class)
                .setParameter("productId", id);
        return q.getResultList();
    }
}
