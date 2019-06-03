package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.UserDao;
import org.bakery.orders.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@ApplicationScoped
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User searchByKeycloakId(@NotNull String keycloakId) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.keycloakId = :keycloakId", User.class)
                .setParameter("keycloakId", keycloakId);
        User user;
        try {
            user = q.getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
        return user;
    }
}
