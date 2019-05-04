package org.bakery.orders.dao.impl;

import org.bakery.orders.dao.CategoryDao;
import org.bakery.orders.entity.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@ApplicationScoped
@Transactional
public class CategoryImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {
    public CategoryImpl() {
        super(Category.class);
    }
}
