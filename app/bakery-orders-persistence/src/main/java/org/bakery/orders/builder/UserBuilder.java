package org.bakery.orders.builder;

import org.bakery.orders.entity.User;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */
public final class UserBuilder {
    private String keycloakId;
    private String name;
    private String email;
    private String passwordHash;
    private String deliveryAddress;
    private String phone;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withKeycloakId(String kcId) {
        this.keycloakId = kcId;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public UserBuilder withDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public UserBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User build() {
        User user = new User();
        user.setKeycloakId(keycloakId);
        user.setName(name);
        user.setEmail(email);
        //user.setDeliveryAddress(deliveryAddress);
        user.setPhone(phone);
        return user;
    }
}
