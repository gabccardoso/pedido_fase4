package com.fiap.pedido.infrastructure.gateways;


import com.fiap.pedido.application.gateways.ProductGateway;
import com.fiap.pedido.entities.Product;
import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.ProductEntity;
import com.fiap.pedido.infrastructure.persistence.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryGateway implements ProductGateway {

  private final ProductRepository productRepository;
  private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        this.productRepository = productRepository;
        this.productEntityMapper = productEntityMapper;
    }


    @Override
    public List<Product> findByCategory(Category category) {
        List<ProductEntity> productsEntity = productRepository.findByCategory(category);
        List<Product> products = new ArrayList<>();
        for(ProductEntity productEntity : productsEntity){
            products.add(productEntityMapper.toObjDomain(productEntity));
        }
        return products;
    }

    @Override
    public Long createProduct(Product product) {
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    public Product editProduct(Long id, Product product) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ProductEntity newProduct = productEntityMapper.toEntity(product);

        productEntity.setCategory(newProduct.getCategory());
        productEntity.setDescription(newProduct.getDescription());
        productEntity.setName(newProduct.getName());
        productEntity.setImage(newProduct.getImage());
        productEntity.setPrice(newProduct.getPrice());

        productRepository.save(productEntity);
        return productEntityMapper.toObjDomain(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for(ProductEntity productEntity : productEntityList){
            products.add(productEntityMapper.toObjDomain(productEntity));
        }
        return  products;
    }
}
