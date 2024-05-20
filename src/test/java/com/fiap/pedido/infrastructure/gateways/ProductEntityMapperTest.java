package com.fiap.pedido.infrastructure.gateways;

import com.fiap.pedido.entities.Product;
import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductEntityMapperTest {

    @Mock
    private Product productMock;

    @Mock
    private ProductEntity productEntityMock;

    @Test
    void testToEntity() {
        openMocks(this);

        // Configuração do objeto mock Product
        when(productMock.name()).thenReturn("Product");
        when(productMock.category()).thenReturn(Category.LANCHE);
        when(productMock.description()).thenReturn("Product description");
        when(productMock.image()).thenReturn("product.jpg");
        when(productMock.price()).thenReturn(100.0);
        when(productMock.itens()).thenReturn(null);

        // Criação do objeto mapper
        ProductEntityMapper mapper = new ProductEntityMapper();

        // Conversão do objeto mock Product para Entity
        ProductEntity productEntity = mapper.toEntity(productMock);

        // Verificação dos resultados
        assertEquals(productMock.name(), productEntity.getName());
        assertEquals(productMock.category(), productEntity.getCategory());
        assertEquals(productMock.description(), productEntity.getDescription());
        assertEquals(productMock.image(), productEntity.getImage());
        assertEquals(productMock.price(), productEntity.getPrice());
        assertEquals(productMock.itens(), productEntity.getItens());
    }

    @Test
    void testToObjDomain() {
        openMocks(this);

        // Configuração do objeto mock ProductEntity
        when(productEntityMock.getName()).thenReturn("Product");
        when(productEntityMock.getCategory()).thenReturn(Category.LANCHE);
        when(productEntityMock.getDescription()).thenReturn("Product description");
        when(productEntityMock.getImage()).thenReturn("product.jpg");
        when(productEntityMock.getPrice()).thenReturn(100.0);
        when(productEntityMock.getItens()).thenReturn(null);

        // Criação do objeto mapper
        ProductEntityMapper mapper = new ProductEntityMapper();

        // Conversão do objeto mock ProductEntity para Product
        Product product = mapper.toObjDomain(productEntityMock);

        // Verificação dos resultados
        assertEquals(productEntityMock.getName(), product.name());
        assertEquals(productEntityMock.getCategory(), product.category());
        assertEquals(productEntityMock.getDescription(), product.description());
        assertEquals(productEntityMock.getImage(), product.image());
        assertEquals(productEntityMock.getPrice(), product.price());
        assertEquals(productEntityMock.getItens(), product.itens());
    }
}
