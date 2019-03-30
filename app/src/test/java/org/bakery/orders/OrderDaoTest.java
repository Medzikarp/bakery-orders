package org.bakery.orders;

import org.bakery.orders.model.Order;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Lukas Kotol on 30.03.2019.
 */


@RunWith(Arquillian.class)
public class OrderDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addClass(Order.class);
    }

    @Test
    public void createOrderTest() {
        System.out.println("hello");
    }

}
