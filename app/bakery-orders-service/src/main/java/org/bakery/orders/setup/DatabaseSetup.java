package org.bakery.orders.setup;

import org.bakery.orders.builder.CategoryBuilder;
import org.bakery.orders.builder.DeliveryOrderBuilder;
import org.bakery.orders.builder.ProductBuilder;
import org.bakery.orders.builder.UserBuilder;
import org.bakery.orders.entity.Category;
import org.bakery.orders.entity.DeliveryOrder;
import org.bakery.orders.entity.Product;
import org.bakery.orders.entity.State;
import org.bakery.orders.entity.User;
import org.bakery.orders.service.CategoryService;
import org.bakery.orders.service.DeliveryOrderProductService;
import org.bakery.orders.service.DeliveryOrderService;
import org.bakery.orders.service.ProductService;
import org.bakery.orders.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Arrays;

/**
 * Created by Lukas Kotol on 14.04.2019.
 */

@Singleton
@Startup
public class DatabaseSetup {

    @Inject
    private DeliveryOrderService deliveryOrderService;

    @Inject
    private UserService userService;

    @Inject
    private DeliveryOrderProductService deliveryOrderProductService;

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        User user1 = UserBuilder.anUser()
                .withEmail("test@test.com")
                .withName("User 1")
                .withPasswordHash("qiyh4XPJGsOZ2MEAyLkfWqeQ")
                .withDeliveryAddress("Kanadska 3, Brno")
                .build();
        userService.create(user1);

        DeliveryOrder order1 = deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("DeliveryOrder 1").withState(State.UNCONFIRMED).build());
        DeliveryOrder order2 = deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("DeliveryOrder 2").withState(State.CONFIRMED).build());
        DeliveryOrder order3 = deliveryOrderService.create(DeliveryOrderBuilder.aDeliveryOrder().withUser(user1).withName("DeliveryOrder 3").withState(State.CONFIRMED).build());

        Category category1 = categoryService.create(CategoryBuilder.aCategory().withDescription("Best sweet pastry for your pleasure.").withName("Sweet pastry").build());
        Category category2 = categoryService.create(CategoryBuilder.aCategory().withDescription("Suitable food for beer.").withName("Salty pastry").build());
        Category category3 = categoryService.create(CategoryBuilder.aCategory().withDescription("Fresh dark pastry").withName("Dark pastry").build());


        Product product1 = productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withDescription("Hand made baked biscuit.").withName("Biscuit").withImage(".png").withCategories(Arrays.asList(category1)).build());
        Product product2 = productService.create(ProductBuilder.aProduct().withCost(100L).withTax(5L).withDescription("American muffins are similar to cupcakes in size and cooking methods.").withName("Muffin").withImage(".png").withCategories(Arrays.asList(category1)).build());
        Product product3 = productService.create(ProductBuilder.aProduct().withCost(1000L).withTax(5L).withDescription("A form of sweet dessert that is typically baked. In its oldest forms, cakes were modifications of breads but now cover a wide range of preparations that can be simple or elaborate").withName("Cake").withImage(".png").withCategories(Arrays.asList(category1, category3)).build());

        productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withImage(".png").withDescription("Very good bread.").withName("Bread").withCategories(Arrays.asList(category2)).build());
        productService.create(ProductBuilder.aProduct().withCost(100L).withTax(5L).withImage(".png").withDescription("Crisp croissant with butter.").withName("Croissant").withCategories(Arrays.asList(category1)).build());
        productService.create(ProductBuilder.aProduct().withCost(100L).withTax(5L).withImage(".png").withDescription("Sesame salty stick.").withName("Stick").withCategories(Arrays.asList(category2)).build());
        productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withImage(".png").withDescription("A baked dish which is usually made of a pastry dough casing that covers or completely contains a filling of various sweet or savoury ingredients.").withName("Pie").withCategories(Arrays.asList(category1)).build());
        productService.create(ProductBuilder.aProduct().withCost(1L).withTax(5L).withImage(".png").withDescription("A small, often round loaf of bread served as a meal accompaniment.").withName("Bread roll").withCategories(Arrays.asList(category2)).build());
        productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withImage(".png").withDescription("A bread made with flour, water and salt, and then thoroughly rolled into flattened dough.").withName("Flatbread").withCategories(Arrays.asList(category2)).build());
        productService.create(ProductBuilder.aProduct().withCost(100L).withTax(5L).withImage(".png").withDescription("A flat, baked dessert square that was developed in the United States at the end of the 19th century.").withName("Brownie").withCategories(Arrays.asList(category1, category3)).build());
        productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withImage(".png").withDescription("A bread product originating in Poland, traditionally shaped by hand into the form of a ring from yeasted wheat dough.").withName("Bagel").withCategories(Arrays.asList(category2)).build());
        productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withImage(".png").withDescription("A large, deep dish used both in the oven and as a serving vessel.").withName("Casserole").withCategories(Arrays.asList(category2)).build());
        productService.create(ProductBuilder.aProduct().withCost(10L).withTax(5L).withImage(".png").withDescription("Really tasty donut.").withName("Donut").withCategories(Arrays.asList(category1)).build());

        deliveryOrderProductService.associate(order1.getId(), product1.getId(), 2L);
        deliveryOrderProductService.associate(order1.getId(), product2.getId(), 2L);
        deliveryOrderProductService.associate(order2.getId(), product2.getId(), 2L);
        deliveryOrderProductService.associate(order3.getId(), product3.getId(), 2L);
    }

}
