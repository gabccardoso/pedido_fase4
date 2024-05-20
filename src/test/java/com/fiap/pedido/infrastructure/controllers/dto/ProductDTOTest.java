package com.fiap.pedido.infrastructure.controllers.dto;

import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.OrderItensEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    @Test
    void testConstructorAndGetters() {
        String name = "Product";
        Category category = Category.LANCHE;
        String description = "Product description";
        String image = "product.jpg";
        Double price = 100.0;
        List<OrderItensEntity> itens = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO(name, category, description, image, price, itens);

        assertEquals(name, productDTO.getName());
        assertEquals(category, productDTO.getCategory());
        assertEquals(description, productDTO.getDescription());
        assertEquals(image, productDTO.getImage());
        assertEquals(price, productDTO.getPrice());
        assertNotNull(productDTO.getItens());
        assertEquals(itens, productDTO.getItens());
    }

    @Test
    void testSetters() {
        ProductDTO productDTO = new ProductDTO();

        String name = "Product";
        Category category = Category.LANCHE;
        String description = "Product description";
        String image = "product.jpg";
        Double price = 100.0;
        List<OrderItensEntity> itens = new ArrayList<>();

        productDTO.setName(name);
        productDTO.setCategory(category);
        productDTO.setDescription(description);
        productDTO.setImage(image);
        productDTO.setPrice(price);
        productDTO.setItens(itens);

        assertEquals(name, productDTO.getName());
        assertEquals(category, productDTO.getCategory());
        assertEquals(description, productDTO.getDescription());
        assertEquals(image, productDTO.getImage());
        assertEquals(price, productDTO.getPrice());
        assertNotNull(productDTO.getItens());
        assertEquals(itens, productDTO.getItens());
    }
}