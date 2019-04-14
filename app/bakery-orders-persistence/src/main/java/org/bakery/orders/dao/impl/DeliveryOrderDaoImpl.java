package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
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
    public List<DeliveryOrder> searchByUser(@NotNull User user) {
        TypedQuery<DeliveryOrder> q = em.createQuery("SELECT o FROM DeliveryOrder o WHERE o.user = :userId", DeliveryOrder.class)
                .setParameter("userId", user.getId());
        return q.getResultList();
    }
}
