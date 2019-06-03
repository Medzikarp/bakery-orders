package org.bakery.orders.entity;


import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@Entity
public class User extends PersistentObject {

    @Column
    @NotNull
    private String keycloakId;

    @Column
    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @Column
    @Email
    @NotNull
    private String email;


    //@Column
    //@NotNull
    //private String deliveryAddress;

    @Column
    @Size(min = 8, max = 14)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private UserRole userRole;

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

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

    /*public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
*/
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