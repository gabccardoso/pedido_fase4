package com.fiap.pedido.infrastructure.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderItensRepositoryTest {
    @Mock
    OrderItensRepository orderItensRepository;

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
    void devePermitirCriarOrderItem(){
        OrderItensEntity orderItens = new OrderItensEntity(1L, 12, 1L);

        when(orderItensRepository.save(any(OrderItensEntity.class))).thenReturn(orderItens);

        OrderItensEntity orderItensCriado = orderItensRepository.save(orderItens);
        //Assert
        verify(orderItensRepository, times(1)).save(any(OrderItensEntity.class));
        Assertions.assertEquals(orderItens, orderItensCriado);
    }

    @Test
    void devePermitirBuscarOrderItemPorId(){
        OrderItensEntity orderItens = new OrderItensEntity(1L, 12, 1L);
        //Arrange
        when(orderItensRepository.findById(any())).thenReturn(Optional.of(orderItens));
        //Act
        Optional<OrderItensEntity> orderItensRetornado = orderItensRepository.findById(anyLong());
        //Assert
        verify(orderItensRepository, times(1)).findById(anyLong());
        Assertions.assertEquals(orderItens, orderItensRetornado.get());
    }

    @Test
    void devePermitirDeletarOrderItem(){
        orderItensRepository.deleteById(any());
        verify(orderItensRepository, times(1)).deleteById(any());
    }

    @Test
    void devePermitirListaOrderItem(){
        OrderItensEntity orderItens1 = new OrderItensEntity(1L, 10, 1L);
        OrderItensEntity orderItens2 = new OrderItensEntity(2L, 12, 1L);

        List<OrderItensEntity> orderItensList = Arrays.asList(orderItens1, orderItens2);

        when(orderItensRepository.findAll()).thenReturn(orderItensList);

        List<OrderItensEntity> orderItensRetornado = orderItensRepository.findAll();

        verify(orderItensRepository, times(1)).findAll();
        Assertions.assertEquals(orderItensList, orderItensRetornado);

    }
}
