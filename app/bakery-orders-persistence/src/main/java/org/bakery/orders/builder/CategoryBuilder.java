package org.bakery.orders.builder;

import org.bakery.orders.entity.Category;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */
public final class CategoryBuilder {
    private String name;
    private String description;

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

    public Category build() {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return category;
    }
}
