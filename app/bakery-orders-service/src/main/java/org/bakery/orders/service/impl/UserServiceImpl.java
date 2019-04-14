package org.bakery.orders.service.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.UserService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Inject
    private UserDao userDao;

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Override
    public User create(User user) {
        User created = userDao.create(user);
        LOGGER.info("User created with id " + user.getId());
        return created;
    }

    @Override
    public User update(User user) {
        LOGGER.info("Updating User with id " + user.getId());
        User updated = userDao.update(user);
        return updated;
    }

    @Override
    public void remove(User user) {
        LOGGER.info("Removing User with id " + user.getId());
        deliveryOrderDao.searchByUser(user.getId()).forEach(deliveryOrder -> deliveryOrder.setUser(null));
        userDao.remove(user.getId());
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("Searching for User with id " + id);
        return userDao.find(id);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Searching for all Users");
        return userDao.findAll();
    }

    @Override
    public void removeAll() {
        LOGGER.info("Removing all Users");
        userDao.removeAll();
    }
}
