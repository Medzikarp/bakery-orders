package org.bakery.orders.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@Entity
public class User extends PersistentObject {

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String passwordHash;

    @Column
    private String deliveryAddress;

    @Column
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
