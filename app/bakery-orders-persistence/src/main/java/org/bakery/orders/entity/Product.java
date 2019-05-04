package org.bakery.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lukas Kotol on 15.04.2019.
 */

@Entity
public class Product extends PersistentObject {

    @Column
    @NotNull
    @Size(max=100)
    private String name;

    @Column
    @PositiveOrZero
    @NotNull
    private Long cost;

    @Column
    @PositiveOrZero
    @NotNull
    private Long tax;

    @Column
    private String image;

    @Column
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
