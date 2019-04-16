package org.bakery.orders.service;

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
 * Created by Lukas Kotol on 16.04.2019.
 */

@RunWith(Arquillian.class)
public class ProductServiceTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private ProductService productService;

    @Before
    public void beforeEach() {
        productService.removeAll();
    }

    @Test
    public void createProductTest() {
        Product product = getSampleProduct();
        productService.create(product);
        Assert.assertEquals(1, productService.findAll().size());
    }

    private Product getSampleProduct() {
        return ProductBuilder
                .aProduct()
                .withName("Product 1")
                .withDescription("Very good product")
                .withImage("Image 1")
                .withCost(10L)
                .withTax(12L)
                .build();
    }

}
