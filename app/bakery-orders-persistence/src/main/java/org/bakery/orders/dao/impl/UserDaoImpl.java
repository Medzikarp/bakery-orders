package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@ApplicationScoped
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }
}
