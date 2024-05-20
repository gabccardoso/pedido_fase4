package com.fiap.pedido.infrastructure.controllers.dto;


import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.OrderItensEntity;

import java.util.List;

public class ProductDTO {

    private String name;
    private Category category;
    private String description;
    private String image;
    private Double price;
    List<OrderItensEntity> itens;

    public ProductDTO() {
    }

    public ProductDTO(String name, Category category, String description, String image, Double price, List<OrderItensEntity> itens) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.itens = itens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderItensEntity> getItens() {
        return itens;
    }

    public void setItens(List<OrderItensEntity> itens) {
        this.itens = itens;
    }
}
