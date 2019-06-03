package org.bakery.orders.dao;

import org.bakery.orders.entity.User;

import javax.validation.constraints.NotNull;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */
public interface UserDao extends GenericDao<User, Long> {
    User searchByKeycloakId(@NotNull String keycloakId);
}
