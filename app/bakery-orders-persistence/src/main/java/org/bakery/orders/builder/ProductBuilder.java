package org.bakery.orders.builder;

import org.bakery.orders.entity.Category;
import org.bakery.orders.entity.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */
public final class ProductBuilder {
    private String name;
    private Long cost;
    private Long tax;
    private String image;
    private String description;
    private List<Category> categories;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withCost(Long cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder withTax(Long tax) {
        this.tax = tax;
        return this;
    }

    public ProductBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        product.setTax(tax);
        product.setImage(image);
        product.setDescription(description);
        product.setCategories(categories);
        return product;
    }
}
