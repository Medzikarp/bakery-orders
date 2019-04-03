package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.entity.DeliveryOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@ApplicationScoped
@Transactional
public class DeliveryOrderDaoImpl implements DeliveryOrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(DeliveryOrder deliveryOrder) {
        entityManager.persist(deliveryOrder);
    }

    @Override
    public Optional<DeliveryOrder> get(long id) {
        return Optional.ofNullable(entityManager.find(DeliveryOrder.class, id));
    }

    @Override
    public List<DeliveryOrder> getAll() {
        return entityManager.createQuery("SELECT o FROM DeliveryOrder o").getResultList();
    }

    @Override
    public void update(DeliveryOrder deliveryOrder) {
        entityManager.merge(deliveryOrder);
    }

    @Override
    public void delete(DeliveryOrder deliveryOrder) {
        deliveryOrder = entityManager.merge(deliveryOrder);
        entityManager.remove(deliveryOrder);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM DeliveryOrder d").executeUpdate();
    }
}
