package org.bakery.orders.service.impl;

import org.bakery.orders.dao.DeliveryOrderDao;
import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Lukas Kotol on 04.04.2019.
 */

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private DeliveryOrderDao deliveryOrderDao;

    @Override
    public User create(User user) {
        User created = userDao.create(user);
        return created;
    }

    @Override
    public User update(User user) {
        User updated = userDao.update(user);
        return updated;
    }

    @Override
    public void remove(User user) {
        deliveryOrderDao.searchByUser(user).forEach(deliveryOrder -> deliveryOrder.setUser(null));
        userDao.remove(user.getId());
    }

    @Override
    public User findById(Long id) {
        return userDao.find(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void removeAll() {
        userDao.removeAll();
    }
}
