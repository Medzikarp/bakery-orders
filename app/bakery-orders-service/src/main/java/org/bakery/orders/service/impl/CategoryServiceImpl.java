package org.bakery.orders.service.impl;

import org.bakery.orders.dao.CategoryDao;
import org.bakery.orders.entity.Category;
import org.bakery.orders.service.CategoryService;
import org.bakery.orders.service.GenericCRUDService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class.getName());

    @Inject
    private CategoryDao categoryDao;

    @Override
    public Category create(Category category) {
        Category created = categoryDao.create(category);
        LOGGER.info("Category created with id " + created.getId());
        return created;
    }

    @Override
    public Category update(Category category) {
        Category updated = categoryDao.update(category);
        LOGGER.info("Updating Category with id " + updated.getId());
        return updated;
    }

    @Override
    public void remove(Category category) {
        LOGGER.info("Removing Category with id " + category.getId());
        categoryDao.remove(category.getId());
    }

    @Override
    public Category findById(Long id) {
        LOGGER.info("Searching for Category with id " + id);
        return categoryDao.find(id);
    }

    @Override
    public List<Category> findAll() {
        LOGGER.info("Searching for all categories");
        return categoryDao.findAll();
    }

    @Override
    public void removeAll() {
        LOGGER.info("Removing all categories");
        categoryDao.removeAll();
    }
}
