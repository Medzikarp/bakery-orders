package org.bakery.orders.service.impl;

import org.bakery.orders.dao.CategoryDao;
import org.bakery.orders.dao.ProductDao;
import org.bakery.orders.entity.Category;
import org.bakery.orders.entity.Product;
import org.bakery.orders.service.CategoryService;
import org.bakery.orders.service.GenericCRUDService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class.getName());

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private ProductDao productDao;

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
        Category toRemove = findById(category.getId());
        toRemove.getProducts().forEach(product -> {
            List<Category> categories = product.getCategories();
            categories.remove(category);
            product.setCategories(categories);
            productDao.update(product);
        });
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
