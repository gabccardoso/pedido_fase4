package com.fiap.pedido.infrastructure.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderRepositoryTest {
    @Mock
    OrderRepository orderRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarOrder(){
        OrderEntity order = new OrderEntity(1L, new ArrayList<>());

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(order);

        OrderEntity orderCriado = orderRepository.save(order);
        //Assert
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        Assertions.assertEquals(order, orderCriado);
    }

    @Test
    void devePermitirBuscarOrderPorId(){
        OrderEntity order = new OrderEntity(1L, new ArrayList<>());
        //Arrange
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        //Act
        Optional<OrderEntity> orderItensRetornado = orderRepository.findById(anyLong());
        //Assert
        verify(orderRepository, times(1)).findById(anyLong());
        Assertions.assertEquals(order, orderItensRetornado.get());
    }

    @Test
    void devePermitirDeletarOrder(){
        orderRepository.deleteById(any());
        verify(orderRepository, times(1)).deleteById(any());
    }

    @Test
    void devePermitirListaOrder(){
        OrderEntity order1 = new OrderEntity(1L, new ArrayList<>());
        OrderEntity order2 = new OrderEntity(2L, new ArrayList<>());

        List<OrderEntity> orderItensList = Arrays.asList(order1, order2);

        when(orderRepository.findAll()).thenReturn(orderItensList);

        List<OrderEntity> ordersRetornados = orderRepository.findAll();

        verify(orderRepository, times(1)).findAll();
        Assertions.assertEquals(orderItensList, ordersRetornados);

    }
}
