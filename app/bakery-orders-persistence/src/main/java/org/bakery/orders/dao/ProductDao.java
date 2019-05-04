package org.bakery.orders.dao;

import org.bakery.orders.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Lukas Kotol on 15.04.2019.
 */
public interface ProductDao extends GenericDao<Product, Long>{
    List<Product> searchByCategory(@NotNull Long id);
}
