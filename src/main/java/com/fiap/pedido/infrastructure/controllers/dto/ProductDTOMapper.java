package com.fiap.pedido.infrastructure.controllers.dto;


import com.fiap.pedido.entities.Product;

public class ProductDTOMapper {

    public ProductDTO toProductDTO(Product productObjDomain){
        return new ProductDTO(productObjDomain.name(), productObjDomain.category(), productObjDomain.description(),
                productObjDomain.image(), productObjDomain.price(), productObjDomain.itens());
    }

    public Product toProduct(ProductDTO productDTO){
        return new Product(productDTO.getName(), productDTO.getCategory(), productDTO.getDescription(),
                productDTO.getImage(), productDTO.getPrice(), productDTO.getItens());
    }
}
