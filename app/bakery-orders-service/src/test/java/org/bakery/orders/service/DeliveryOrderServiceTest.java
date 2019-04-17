package org.bakery.orders.service;

import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */

@RunWith(Arquillian.class)
public class DeliveryOrderServiceTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private DeliveryOrderService deliveryOrderService;

    @Inject
    private UserService userService;

    @Before
    public void beforeEach() {
        userService.removeAll();
        deliveryOrderService.removeAll();
    }

    @Test
    public void createDeliveryOrderTest() {
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrderService.create(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderService.findAll().size());
    }

    @Test
    public void addUserToDeliveryOrderTest() {
        User user = getSampleUser();
        userService.create(user);
        DeliveryOrder deliveryOrder = getSampleOrder();
        deliveryOrder.setUser(user);
        deliveryOrderService.create(deliveryOrder);
        DeliveryOrder expected = deliveryOrderService.findById(deliveryOrder.getId());
        Assert.assertEquals(user.getName(), expected.getUser().getName());
    }

    private DeliveryOrder getSampleOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName("Objednavka 1");
        return deliveryOrder;
    }

    private User getSampleUser() {
        User user = new User();
        user.setName("Jan Novak");
        return user;
    }

}
