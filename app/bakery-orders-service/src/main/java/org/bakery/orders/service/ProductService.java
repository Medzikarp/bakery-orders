package org.bakery.orders.service;

import org.bakery.orders.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public interface ProductService extends GenericCRUDService<Product, Long> {
    List<Product> searchByCategory(@NotNull Long id);
}
