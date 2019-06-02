package org.bakery.orders.service;

import org.bakery.orders.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Lukas Kotol on 16.04.2019.
 */
public interface ProductService extends GenericCRUDService<Product, Long> {
    Future<Product> createAsync(Product product);

    void removeByJMS(Product product);

    List<Product> searchByCategory(@NotNull Long id);
}
