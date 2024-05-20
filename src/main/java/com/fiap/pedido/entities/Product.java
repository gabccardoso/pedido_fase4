package com.fiap.pedido.entities;


import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.OrderItensEntity;

import java.util.List;

public record Product(String name, Category category, String description, String image, Double price, List<OrderItensEntity> itens) {
}
