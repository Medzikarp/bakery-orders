package org.bakery.orders.dao;

import org.bakery.orders.builder.ProductBuilder;
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

/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@RunWith(Arquillian.class)
public class ProductDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private ProductDao productDao;

    @Before
    public void beforeEach() {
        productDao.removeAll();
    }

    @Test
    public void createProductTest() {
        Product product = createProduct();
        productDao.create(product);
        Assert.assertEquals(1, productDao.findAll().size());
        Assert.assertEquals(product.getName(), productDao.findAll().get(0).getName());
    }

    @Test
    public void deleteAllProductsTest() {
        productDao.create(createProduct());
        productDao.removeAll();
        Assert.assertEquals(0, productDao.findAll().size());
    }

    @Test
    public void updateProductTest() {
        Product product = createProduct();
        productDao.create(product);
        product.setName("Product 2");
        productDao.update(product);
        Assert.assertEquals(1, productDao.findAll().size());
        Assert.assertEquals(product.getName(), productDao.findAll().get(0).getName());
    }

    @Test
    public void deleteProductTest() {
        Product product = createProduct();
        productDao.create(product);
        productDao.remove(product.getId());
        Assert.assertEquals(0, productDao.findAll().size());
    }

    private Product createProduct() {
        return ProductBuilder.aProduct()
                .withCost(10L)
                .withDescription("Good product")
                .withName("Product 1")
                .withTax(10L)
                .withImage("Simple Imange")
                .build();
    }
}
