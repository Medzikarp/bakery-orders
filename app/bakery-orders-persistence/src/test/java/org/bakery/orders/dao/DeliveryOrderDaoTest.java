package org.bakery.orders.dao;

import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.State;
import org.bakery.orders.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;


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

    @Inject
    private UserDao userDao;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void beforeEach() {
        deliveryOrderDao.removeAll();
        userDao.removeAll();
    }

    @Test
    public void createOrderTest() {
        User user = getSampleUser();
        DeliveryOrder deliveryOrder = getSampleOrder(user);
        userDao.create(user);
        deliveryOrderDao.create(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderDao.findAll().size());
        Assert.assertEquals(deliveryOrder.getName(), deliveryOrderDao.findAll().get(0).getName());
    }


    @Test
    public void deleteAllOrderTest() {
        User user = getSampleUser();
        DeliveryOrder deliveryOrder = getSampleOrder(user);
        userDao.create(user);
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrderDao.removeAll();
        Assert.assertEquals(0, deliveryOrderDao.findAll().size());
    }

    @Test
    public void updateOrderTest() {
        User user = getSampleUser();
        DeliveryOrder deliveryOrder = getSampleOrder(user);
        userDao.create(user);
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrder.setName("Zmena");
        deliveryOrderDao.update(deliveryOrder);
        Assert.assertEquals(1, deliveryOrderDao.findAll().size());
        Assert.assertEquals(deliveryOrder.getName(), deliveryOrderDao.findAll().get(0).getName());
    }

    @Test
    public void deleteOrderTest() {
        User user = getSampleUser();
        DeliveryOrder deliveryOrder = getSampleOrder(user);
        userDao.create(user);
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrderDao.remove(deliveryOrder.getId());
        Assert.assertEquals(0, deliveryOrderDao.findAll().size());
    }

    @Test
    public void searchByUserNullTest() {
        expectedException.expect(ConstraintViolationException.class);
        deliveryOrderDao.searchByKeycloakId(null);
    }

    private User getSampleUser() {
        return UserBuilder.anUser().withEmail("test@test.com")
                .withKeycloakId("bfcdd542-04dc-44b9-94ef-6a172e4e02f7")
                .withName("Jan Novak")
                .withPasswordHash("qiyh4XPJGsOZ2MEAyLkfWqeQ")
                .withDeliveryAddress("Kanadska 3, Brno")
                .build();
    }

    private DeliveryOrder getSampleOrder(User user) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setUser(user);
        deliveryOrder.setName("Objednavka 1");
        deliveryOrder.setCreatedAt(LocalDateTime.now());
        deliveryOrder.setState(State.UNCONFIRMED);
        return deliveryOrder;
    }


}
