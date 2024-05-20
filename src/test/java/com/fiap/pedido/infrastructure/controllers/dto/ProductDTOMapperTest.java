package com.fiap.pedido.infrastructure.controllers.dto;

import com.fiap.pedido.entities.Product;
import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.OrderItensEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductDTOMapperTest {

    @Mock
    private Product productMock;

    @Test
    void testToProductDTO() {
        openMocks(this);

        // Configuração do objeto mock
        when(productMock.name()).thenReturn("Product");
        when(productMock.category()).thenReturn(Category.LANCHE);
        when(productMock.description()).thenReturn("Product description");
        when(productMock.image()).thenReturn("product.jpg");
        when(productMock.price()).thenReturn(100.0);
        when(productMock.itens()).thenReturn(null);

        // Criação do objeto mapper
        ProductDTOMapper mapper = new ProductDTOMapper();

        // Conversão do objeto mock para DTO
        ProductDTO productDTO = mapper.toProductDTO(productMock);

        // Verificação dos resultados
        assertEquals(productMock.name(), productDTO.getName());
        assertEquals(productMock.category(), productDTO.getCategory());
        assertEquals(productMock.description(), productDTO.getDescription());
        assertEquals(productMock.image(), productDTO.getImage());
        assertEquals(productMock.price(), productDTO.getPrice());
        assertEquals(productMock.itens(), productDTO.getItens());
    }

    @Test
    void testToProduct() {
        // Criação de um objeto DTO
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product");
        productDTO.setCategory(Category.LANCHE);
        productDTO.setDescription("Product description");
        productDTO.setImage("product.jpg");
        productDTO.setPrice(100.0);
        productDTO.setItens(null);

        // Criação do objeto mapper
        ProductDTOMapper mapper = new ProductDTOMapper();

        // Conversão do DTO para objeto
        Product product = mapper.toProduct(productDTO);

        // Verificação dos resultados
        assertEquals(productDTO.getName(), product.name());
        assertEquals(productDTO.getCategory(), product.category());
        assertEquals(productDTO.getDescription(), product.description());
        assertEquals(productDTO.getImage(), product.image());
        assertEquals(productDTO.getPrice(), product.price());
        assertEquals(productDTO.getItens(), product.itens());
    }
}