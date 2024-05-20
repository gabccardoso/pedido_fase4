package com.fiap.pedido.infrastructure.gateways;

import com.fiap.pedido.entities.Product;
import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.ProductEntity;
import com.fiap.pedido.infrastructure.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductRepositoryGatewayTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private ProductRepositoryGateway productRepositoryGateway;

    @Test
    void testFindByCategory() {
        // Configuração do mock
        List<ProductEntity> productEntities = new ArrayList<>();
        when(productRepository.findByCategory(Category.LANCHE)).thenReturn(productEntities);
        when(productEntityMapper.toObjDomain(any())).thenReturn(
                new Product("hamburguer", Category.LANCHE, "descricao", "image", 12.00, new ArrayList<>()));

        // Execução do método a ser testado
        List<Product> products = productRepositoryGateway.findByCategory(Category.LANCHE);

        // Verificações
        assertNotNull(products);
        assertTrue(products.isEmpty());
        verify(productRepository, times(1)).findByCategory(Category.LANCHE);
        verify(productEntityMapper, times(0)).toObjDomain(any());
    }

    @Test
    void testCreateProduct() {
        // Configuração do mock
        Product product = new Product("hamburguer", Category.LANCHE, "descricao", "image", 12.00, new ArrayList<>());
        ProductEntity productEntity = new ProductEntity("hamburguer", Category.LANCHE, "descricao", "image", 12.00, new ArrayList<>());
        when(productEntityMapper.toEntity(product)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(productEntity);

        // Execução do método a ser testado
        Long id = productRepositoryGateway.createProduct(product);

        // Verificações
        verify(productEntityMapper, times(1)).toEntity(product);
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void testEditProduct() {
        // Configuração do mock
        Long id = 1L;
        Product product = new Product("hamburguer", Category.LANCHE, "descricao", "image", 12.00, new ArrayList<>());
        ProductEntity productEntity = new ProductEntity();
        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productEntityMapper.toEntity(product)).thenReturn(productEntity);
        when(productEntityMapper.toObjDomain(productEntity)).thenReturn(product);

        // Execução do método a ser testado
        Product editedProduct = productRepositoryGateway.editProduct(id, product);

        // Verificações
        assertNotNull(editedProduct);
        assertEquals(product, editedProduct);
        verify(productRepository, times(1)).findById(id);
        verify(productEntityMapper, times(1)).toEntity(product);
        verify(productEntityMapper, times(1)).toObjDomain(productEntity);
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void testDeleteProduct() {
        // Execução do método a ser testado
        assertDoesNotThrow(() -> productRepositoryGateway.deleteProduct(1L));

        // Verificações
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindProducts() {
        // Configuração do mock
        List<ProductEntity> productEntities = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productEntities);
        when(productEntityMapper.toObjDomain(any())).thenReturn(new Product("hamburguer", Category.LANCHE, "descricao", "image", 12.00, new ArrayList<>()));

        // Execução do método a ser testado
        List<Product> products = productRepositoryGateway.findProducts();

        // Verificações
        assertNotNull(products);
        assertTrue(products.isEmpty());
        verify(productRepository, times(1)).findAll();
        verify(productEntityMapper, times(0)).toObjDomain(any());
    }
}
