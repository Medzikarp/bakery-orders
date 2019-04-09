package org.bakery.orders.entity;


import javax.persistence.*;

/**
 * Created by Lukas Kotol on 30.03.2019.
 */

@Entity
public class DeliveryOrder extends PersistentObject {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
