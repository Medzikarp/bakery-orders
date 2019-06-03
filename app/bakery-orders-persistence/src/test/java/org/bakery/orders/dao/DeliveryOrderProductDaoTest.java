package org.bakery.orders.dao;

import org.bakery.orders.builder.DeliveryOrderBuilder;
import org.bakery.orders.builder.DeliveryOrderProductBuilder;
import org.bakery.orders.builder.ProductBuilder;
import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.entity.*;
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
import java.time.LocalDateTime;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */

@RunWith(Arquillian.class)
public class DeliveryOrderProductDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private DeliveryOrderProductDao deliveryOrderProductDao;

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private UserDao userDao;

    @Before
    public void beforeEach() {
        deliveryOrderProductDao.removeAll();
        deliveryOrderDao.removeAll();
        productDao.removeAll();
        userDao.removeAll();
    }

    @Test
    public void createDeliveryOrderProductTest() {

        User user = getSampleUser();
        DeliveryOrder order = getSampleOrder(user);
        Product product = getSampleProduct();


        userDao.create(user);
        deliveryOrderDao.create(order);
        productDao.create(product);

        DeliveryOrderProduct deliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withQuantity(2L).withProduct(product).withDeliveryOrder(order).build();
        deliveryOrderProductDao.create(deliveryOrderProduct);
        Assert.assertEquals(1, deliveryOrderProductDao.findAll().size());
        Assert.assertEquals(deliveryOrderProduct.getQuantity(), deliveryOrderProductDao.findAll().get(0).getQuantity());
    }

    @Test
    public void searchByDeliveryOrderTest() {
        User user = getSampleUser();
        Product product = getSampleProduct();
        Product anotherProduct = getSampleProduct();
        DeliveryOrder deliveryOrder = getSampleOrder(user);
        DeliveryOrder anotherDeliveryOrder = getSampleOrder(user);

        userDao.create(user);
        productDao.create(product);
        productDao.create(anotherProduct);
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrderDao.create(anotherDeliveryOrder);

        DeliveryOrderProduct deliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withProduct(product).withDeliveryOrder(deliveryOrder).build();
        DeliveryOrderProduct anotherDeliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withProduct(anotherProduct).withDeliveryOrder(deliveryOrder).build();
        DeliveryOrderProduct sameDeliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withProduct(product).withDeliveryOrder(anotherDeliveryOrder).build();


        deliveryOrderProductDao.create(deliveryOrderProduct);
        deliveryOrderProductDao.create(anotherDeliveryOrderProduct);
        deliveryOrderProductDao.create(sameDeliveryOrderProduct);

        Assert.assertEquals(2, deliveryOrderProductDao.searchByDeliveryOrder(deliveryOrder.getId()).size());

    }

    @Test
    public void searchByProductTest() {
        User user = getSampleUser();
        Product product = getSampleProduct();
        Product anotherProduct = getSampleProduct();
        DeliveryOrder deliveryOrder = getSampleOrder(user);
        DeliveryOrder anotherDeliveryOrder = getSampleOrder(user);

        userDao.create(user);
        productDao.create(product);
        productDao.create(anotherProduct);
        deliveryOrderDao.create(deliveryOrder);
        deliveryOrderDao.create(anotherDeliveryOrder);

        DeliveryOrderProduct deliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withProduct(product).withDeliveryOrder(deliveryOrder).build();
        DeliveryOrderProduct anotherDeliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withProduct(anotherProduct).withDeliveryOrder(deliveryOrder).build();
        DeliveryOrderProduct sameDeliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withProduct(product).withDeliveryOrder(anotherDeliveryOrder).build();

        deliveryOrderProductDao.create(deliveryOrderProduct);
        deliveryOrderProductDao.create(anotherDeliveryOrderProduct);
        deliveryOrderProductDao.create(sameDeliveryOrderProduct);

        Assert.assertEquals(2, deliveryOrderProductDao.searchByProduct(product.getId()).size());

    }

    private Product getSampleProduct() {
        return ProductBuilder.aProduct()
                .withCost(10L)
                .withDescription("Good product")
                .withName("Product 1")
                .withTax(10L)
                .withImage("Simple Image")
                .build();
    }

    private DeliveryOrder getSampleOrder(User user) {
        return DeliveryOrderBuilder.aDeliveryOrder()
                .withName("order1")
                .withCreatedAt(LocalDateTime.now())
                .withUser(user)
                .withState(State.UNCONFIRMED)
                .build();
    }

    private User getSampleUser() {
        return  UserBuilder.anUser()
                .withKeycloakId("bfcdd542-04dc-44b9-94ef-6a172e4e02f7")
                .withEmail("test@test.com")
                .withName("Jan Novak")
                .withPasswordHash("qiyh4XPJGsOZ2MEAyLkfWqeQ")
                .withDeliveryAddress("Kanadska 3, Brno")
                .build();
    }

}
