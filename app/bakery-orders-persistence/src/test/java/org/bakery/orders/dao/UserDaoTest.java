package org.bakery.orders.dao;

import org.bakery.orders.entity.User;
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
 * Created by Lukas Kotol on 03.04.2019.
 */

@RunWith(Arquillian.class)
public class UserDaoTest {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "org.bakery.orders")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private UserDao userDao;

    @Before
    public void beforeEach() {
        userDao.removeAll();
    }

    @Test
    public void createOrderTest() {
        User user = getSampleUser();
        userDao.create(user);
        Assert.assertEquals(1, userDao.findAll().size());
        Assert.assertEquals(user.getName(), userDao.findAll().get(0).getName());
    }

    @Test
    public void deleteAllUsersTest() {
        userDao.create(getSampleUser());
        userDao.removeAll();
        Assert.assertEquals(0, userDao.findAll().size());
    }

    @Test
    public void updateUserTest() {
        User user = getSampleUser();
        userDao.create(user);
        user.setName("Petr Maly");
        userDao.update(user);
        Assert.assertEquals(1, userDao.findAll().size());
        Assert.assertEquals(user.getName(), userDao.findAll().get(0).getName());
    }

    @Test
    public void deleteUserTest() {
        User user = getSampleUser();
        userDao.create(user);
        userDao.remove(user.getId());
        Assert.assertEquals(0, userDao.findAll().size());
    }

    private User getSampleUser() {
        User user = new User();
        user.setName("Jan Novak");
        return user;
    }

}
