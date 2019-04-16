package org.bakery.orders.dao;

import org.bakery.orders.builder.DeliveryOrderProductBuilder;
import org.bakery.orders.builder.ProductBuilder;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.DeliveryOrderProduct;
import org.bakery.orders.entity.Product;
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

    @Before
    public void beforeEach() {
        deliveryOrderProductDao.removeAll();
        deliveryOrderDao.removeAll();
        productDao.removeAll();
    }

    @Test
    public void createDeliveryOrderProductTest() {
        DeliveryOrderProduct deliveryOrderProduct = DeliveryOrderProductBuilder.aDeliveryOrderProduct().withQuantity(2L).build();
        deliveryOrderProductDao.create(deliveryOrderProduct);
        Assert.assertEquals(1, deliveryOrderProductDao.findAll().size());
        Assert.assertEquals(deliveryOrderProduct.getQuantity(), deliveryOrderProductDao.findAll().get(0).getQuantity());
    }

    @Test
    public void searchByDeliveryOrderTest() {
        Product product = getSampleProduct();
        Product anotherProduct = getSampleProduct();
        DeliveryOrder deliveryOrder = getSampleOrder();
        DeliveryOrder anotherDeliveryOrder = getSampleOrder();

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
        Product product = getSampleProduct();
        Product anotherProduct = getSampleProduct();
        DeliveryOrder deliveryOrder = getSampleOrder();
        DeliveryOrder anotherDeliveryOrder = getSampleOrder();

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
                .withImage("Simple Imange")
                .build();
    }

    private DeliveryOrder getSampleOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName("Objednavka 1");
        deliveryOrder.setCreatedAt(LocalDateTime.now());
        return deliveryOrder;
    }
}
