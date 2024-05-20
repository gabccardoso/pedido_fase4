package com.fiap.pedido.infrastructure.persistence;

import com.fiap.pedido.infrastructure.controllers.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O nome do produto não pode ser vazio")
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private String image;
    private Double price;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<OrderItensEntity> itens;

    public ProductEntity() {
    }

    public ProductEntity(String name, Category category, String description, String image, Double price, List<OrderItensEntity> itens) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.itens = itens;
    }

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
