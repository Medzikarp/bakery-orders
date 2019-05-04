package org.bakery.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@Entity
public class DeliveryOrderProduct extends PersistentObject {

    @ManyToOne
    @NotNull
    @Valid
    private DeliveryOrder deliveryOrder;

    @ManyToOne
    @NotNull
    @Valid
    private Product product;

    @Column
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
