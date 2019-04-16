package org.bakery.orders.builder;

import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.DeliveryOrderProduct;
import org.bakery.orders.entity.Product;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public final class DeliveryOrderProductBuilder {
    private DeliveryOrder deliveryOrder;
    private Product product;
    private Long quantity;

    private DeliveryOrderProductBuilder() {
    }

    public static DeliveryOrderProductBuilder aDeliveryOrderProduct() {
        return new DeliveryOrderProductBuilder();
    }

    public DeliveryOrderProductBuilder withDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
        return this;
    }

    public DeliveryOrderProductBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public DeliveryOrderProductBuilder withQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public DeliveryOrderProduct build() {
        DeliveryOrderProduct deliveryOrderProduct = new DeliveryOrderProduct();
        deliveryOrderProduct.setDeliveryOrder(deliveryOrder);
        deliveryOrderProduct.setProduct(product);
        deliveryOrderProduct.setQuantity(quantity);
        return deliveryOrderProduct;
    }
}
