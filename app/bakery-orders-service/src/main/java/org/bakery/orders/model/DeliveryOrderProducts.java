package org.bakery.orders.model;

import org.bakery.orders.entity.DeliveryOrderProduct;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas Kotol on 04.05.2019.
 */
public class DeliveryOrderProducts {
    private List<DeliveryOrderProductDTO> deliveryOrderProducts = new LinkedList<>();

    public List<DeliveryOrderProductDTO> getDeliveryOrderProducts() {
        return deliveryOrderProducts;
    }

    public void setDeliveryOrderProducts(List<DeliveryOrderProductDTO> deliveryOrderProducts) {
        this.deliveryOrderProducts = deliveryOrderProducts;
    }
}
