package org.bakery.orders.dao;

import org.bakery.orders.entity.DeliveryOrder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;


/**
 * Created by Lukas Kotol on 30.03.2019.
 */


@RunWith(Arquillian.class)
public class DeliveryOrderDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Before
    public void beforeEach() {
        deliveryOrderDao.deleteAll();
    }

    @Test
    public void createOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderDao.save(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderDao.getAll().size());
        Assert.assertEquals(deliveryOrder.getName(), deliveryOrderDao.getAll().get(0).getName());
    }


    @Test
    public void deleteAllOrderTest() {
        deliveryOrderDao.save(getSampleOrder());
        deliveryOrderDao.deleteAll();
        Assert.assertEquals(0, deliveryOrderDao.getAll().size());
    }

    @Test
    public void updateOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderDao.save(deliveryOrder);
        deliveryOrder.setName("Zmena");
        deliveryOrderDao.update(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderDao.getAll().size());
        Assert.assertEquals(deliveryOrder.getName(), deliveryOrderDao.getAll().get(0).getName());
    }

    @Test
    public void deleteOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderDao.save(deliveryOrder);
        deliveryOrderDao.delete(deliveryOrder);
        Assert.assertEquals(0, deliveryOrderDao.getAll().size());
    }

    private DeliveryOrder getSampleOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName("Objednavka 1");
        return deliveryOrder;
    }

}
