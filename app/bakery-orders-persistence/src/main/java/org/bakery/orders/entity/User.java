package org.bakery.orders.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@Entity
public class User extends PersistentObject {

    @Column
    @NotNull
    private String name;

    @Column
    private String email;

    @Column
    @NotNull
    private String passwordHash;

    @Column
    @NotNull
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
