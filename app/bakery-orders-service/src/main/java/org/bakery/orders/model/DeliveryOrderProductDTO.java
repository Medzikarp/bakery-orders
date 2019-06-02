package org.bakery.orders.model;

import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Lukas Kotol on 04.05.2019.
 */
public class DeliveryOrderProductDTO {

    private DeliveryOrder order;

    private Product product;

    private Long quantity;

    private String inputId;

    public DeliveryOrder getDeliveryOrder() {
        return order;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.order = deliveryOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }
}
