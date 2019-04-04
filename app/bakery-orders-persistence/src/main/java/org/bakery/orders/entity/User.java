package org.bakery.orders.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Created by Lukas Kotol on 03.04.2019.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
