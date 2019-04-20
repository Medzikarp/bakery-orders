package org.bakery.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Lukas Kotol on 20.04.2019.
 */

@Entity
public class Category extends PersistentObject {

    @Column
    private String name;

    @Column
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
