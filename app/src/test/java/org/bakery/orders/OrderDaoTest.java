package org.bakery.orders;

import org.bakery.orders.model.DeliveryOrder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.transaction.api.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by Lukas Kotol on 30.03.2019.
 */


@RunWith(Arquillian.class)
@Transactional
public class OrderDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addClass(DeliveryOrder.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    public void createOrderTest() {
        DeliveryOrder order = new DeliveryOrder();
        order.setName("test");
        order.setId("1");
        entityManager.persist(order);
    }

}
