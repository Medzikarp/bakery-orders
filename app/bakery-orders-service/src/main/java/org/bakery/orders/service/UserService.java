package org.bakery.orders.service;

import org.bakery.orders.entity.User;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */
public interface UserService extends GenericCRUDService<User, Long> {
    public User findByKeycloakId (String keycloakId);
}
