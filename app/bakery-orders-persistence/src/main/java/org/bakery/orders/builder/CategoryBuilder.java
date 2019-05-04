package org.bakery.orders.builder;

import org.bakery.orders.entity.Category;
import org.bakery.orders.entity.Product;

import java.util.List;
import java.util.Set;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */
public final class CategoryBuilder {
    private String name;
    private String description;
    private List<Product> products;

    private CategoryBuilder() {
    }

    public static CategoryBuilder aCategory() {
        return new CategoryBuilder();
    }

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder withProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public Category build() {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setProducts(products);
        return category;
    }
}
