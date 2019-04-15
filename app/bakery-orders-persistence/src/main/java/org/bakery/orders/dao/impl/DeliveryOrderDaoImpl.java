package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.entity.DeliveryOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@ApplicationScoped
@Transactional
public class DeliveryOrderDaoImpl extends GenericDaoImpl<DeliveryOrder, Long> implements DeliveryOrderDao {


    public DeliveryOrderDaoImpl() {
        super(DeliveryOrder.class);
    }

    @Override
    public List<DeliveryOrder> searchByUser(@NotNull Long id) {

        TypedQuery<DeliveryOrder> q = em.createQuery("SELECT o FROM DeliveryOrder o WHERE o.user.id = :userId", DeliveryOrder.class)
                .setParameter("userId", id);
        return q.getResultList();
    }

    @Override
    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        deliveryOrder.setCreatedAt(LocalDateTime.now());
        deliveryOrder.setUpdatedAt();
        em.persist(deliveryOrder);
        return deliveryOrder;
    }
}
