package org.bakery.orders.dao;

import org.bakery.orders.builder.CategoryBuilder;
import org.bakery.orders.entity.Category;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@RunWith(Arquillian.class)
public class CategoryDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private CategoryDao categoryDao;

    @Before
    public void beforeEach() {
        categoryDao.removeAll();
    }

    @Test
    public void createOrderTest() {
        Category category = getSampleCategory();
        categoryDao.create(category);
        Assert.assertEquals(1, categoryDao.findAll().size());
        Assert.assertEquals(category.getName(), categoryDao.findAll().get(0).getName());
    }

    @Test
    public void deleteAllCategoriesTest() {
        categoryDao.create(getSampleCategory());
        categoryDao.removeAll();
        Assert.assertEquals(0, categoryDao.findAll().size());
    }

    @Test
    public void updateCategoryTest() {
        Category category = getSampleCategory();
        categoryDao.create(category);
        category.setName("Category 2");
        categoryDao.update(category);
        Assert.assertEquals(1, categoryDao.findAll().size());
        Assert.assertEquals(category.getName(), categoryDao.findAll().get(0).getName());
    }

    @Test
    public void deleteCategoryTest() {
        Category category = getSampleCategory();
        categoryDao.create(category);
        categoryDao.remove(category.getId());
        Assert.assertEquals(0, categoryDao.findAll().size());
    }


    private Category getSampleCategory() {
        return CategoryBuilder.aCategory()
                .withName("Category 1")
                .withDescription("Best category")
                .build();
    }
}
