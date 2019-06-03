package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.entity.DeliveryOrder;

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
    public List<DeliveryOrder> searchByUser(@NotNull String keycloakId) {

        TypedQuery<DeliveryOrder> q = em.createQuery("SELECT o FROM DeliveryOrder o WHERE o.user.keycloakId = :keycloakId", DeliveryOrder.class)
                .setParameter("keycloakId", keycloakId);
        return q.getResultList();
    }
}
