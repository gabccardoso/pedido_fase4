package com.fiap.pedido.application.gateways;



import com.fiap.pedido.entities.Product;
import com.fiap.pedido.infrastructure.controllers.enums.Category;

import java.util.List;

public interface ProductGateway {
    List<Product> findByCategory(Category category);
    Long createProduct(Product product);
    Product editProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> findProducts();
}
