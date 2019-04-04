package org.bakery.orders.dao;

import java.util.List;

/**
 * @author Lukáš Kotol
 *
 * @param <T> entity type
 * @param <U> identificator type
 */
public interface GenericDao<T, U> {

    T create(final T entity);

    T update(final T entity);

    void remove(U id);

    void removeAll();

    T find(U id);

    List<T> findAll();

}
