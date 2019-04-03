package org.bakery.orders.dao;

import org.bakery.orders.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */
public interface UserDao {
    void save(User deliveryOrder);

    Optional<User> get(long id);

    List<User> getAll();


    void update(User user);

    void delete(User user);

    void deleteAll();
}
