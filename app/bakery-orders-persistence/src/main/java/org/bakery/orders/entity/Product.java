package org.bakery.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@Entity
public class Product extends PersistentObject {

    @Column
    private String name;

    @Column
    private Long cost;

    @Column
    private Long tax;

    @Column
    private String image;

    @Column
    private String description;

    @OneToMany(mappedBy = "product")
    private Set<DeliveryOrderProduct> deliveryOrderProducts = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DeliveryOrderProduct> getDeliveryOrderProducts() {
        return deliveryOrderProducts;
    }

    public void setDeliveryOrderProducts(Set<DeliveryOrderProduct> deliveryOrderProducts) {
        this.deliveryOrderProducts = deliveryOrderProducts;
    }

    public void addDeliveryOrderProduct(DeliveryOrderProduct deliveryOrderProduct) {
        this.deliveryOrderProducts.add(deliveryOrderProduct);
    }
}
