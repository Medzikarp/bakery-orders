package org.bakery.orders.entity;

import javax.persistence.*;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@Entity
public class DeliveryOrderProduct extends PersistentObject {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ORDER_ID")
    private DeliveryOrder deliveryOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
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
