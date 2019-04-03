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
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        user = entityManager.merge(user);
        entityManager.remove(user);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM User u").executeUpdate();
    }
}
