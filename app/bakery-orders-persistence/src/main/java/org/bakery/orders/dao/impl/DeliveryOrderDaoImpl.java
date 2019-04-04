package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.entity.DeliveryOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@ApplicationScoped
@Transactional
public class DeliveryOrderDaoImpl extends GenericDaoImpl<DeliveryOrder, Long> implements DeliveryOrderDao {


    public DeliveryOrderDaoImpl() {
        super(DeliveryOrder.class);
    }
}
