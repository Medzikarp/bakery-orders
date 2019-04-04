package org.bakery.orders.dao;

import org.bakery.orders.dao.DeliveryOrderDao;
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
        deliveryOrderDao.removeAll();
    }

    @Test
    public void createOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderDao.create(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderDao.findAll().size());
        Assert.assertEquals(deliveryOrder.getName(), deliveryOrderDao.findAll().get(0).getName());
    }


    @Test
    public void deleteAllOrderTest() {
        deliveryOrderDao.create(getSampleOrder());
        deliveryOrderDao.removeAll();
        Assert.assertEquals(0, deliveryOrderDao.findAll().size());
    }

    @Test
    public void updateOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrder.setName("Zmena");
        deliveryOrderDao.update(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderDao.findAll().size());
        Assert.assertEquals(deliveryOrder.getName(), deliveryOrderDao.findAll().get(0).getName());
    }

    @Test
    public void deleteOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrderDao.remove(deliveryOrder.getId());
        Assert.assertEquals(0, deliveryOrderDao.findAll().size());
    }

    private DeliveryOrder getSampleOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName("Objednavka 1");
        return deliveryOrder;
    }

}
