package com.fiap.pedido.infrastructure.gateways;


import com.fiap.pedido.entities.Product;
import com.fiap.pedido.infrastructure.persistence.ProductEntity;

public class ProductEntityMapper {

    public ProductEntity toEntity(Product productObjDomain){
        return new ProductEntity(productObjDomain.name(), productObjDomain.category(), productObjDomain.description(),
                productObjDomain.image(), productObjDomain.price(), productObjDomain.itens());
    }

    public Product toObjDomain(ProductEntity productEntity){
        return new Product(productEntity.getName(), productEntity.getCategory(), productEntity.getDescription(),
                productEntity.getImage(), productEntity.getPrice(), productEntity.getItens());
    }
}
