package org.bakery.orders.listeners;

import org.bakery.orders.entity.Product;
import org.bakery.orders.service.ProductService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

/**
 * Created by Lukas Kotol on 16.05.2019.
 */

@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = RemoveProductListener.QUEUE_JNDI,
                interfaceName = "javax.jms.Queue",
                destinationName = "QueueJMS"
        )})
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = RemoveProductListener.QUEUE_JNDI)
})
public class RemoveProductListener implements MessageListener {

    public static final String QUEUE_JNDI = "java:jboss/exported/jms/queue";

    @Inject
    private ProductService productService;

    @Override
    public void onMessage(Message message) {
        try {
            Product product = message.getBody(Product.class);
            productService.remove(product);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
