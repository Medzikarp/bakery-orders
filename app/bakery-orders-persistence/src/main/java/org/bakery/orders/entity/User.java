package org.bakery.orders.entity;


import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@Entity
public class User extends PersistentObject {

    @Column
    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @Column
    @Email
    @NotNull
    private String email;

    @Column
    @NotNull
    private String passwordHash;

    @Column
    @NotNull
    private String deliveryAddress;

    @Column
    @Size(min = 8, max = 14)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private UserRole userRole;

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

enum UserRole {
    ADMIN,
    CUSTOMER
}