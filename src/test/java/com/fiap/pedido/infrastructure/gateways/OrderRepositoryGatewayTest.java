package com.fiap.pedido.infrastructure.gateways;

import com.fiap.pedido.entities.Order;
import com.fiap.pedido.infrastructure.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderRepositoryGatewayTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItensRepository orderItensRepository;
    @Mock
    private OrderEntityMapper orderEntityMapper;

    private OrderRepositoryGateway orderRepositoryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderRepositoryGateway = new OrderRepositoryGateway(orderRepository, orderItensRepository, orderEntityMapper);
    }

    @Test
    void createOrder() {
        Order order = new Order(1L, new Date(), new ArrayList<>());
        OrderEntity orderEntity = new OrderEntity(1L, new ArrayList<>());
        when(orderEntityMapper.toEntity(order)).thenReturn(orderEntity);
        when(orderRepository.save(any())).thenReturn(orderEntity);

        Long orderId = orderRepositoryGateway.createOrder(order);

        assertEquals(orderEntity.getId(), orderId);
        verify(orderRepository, times(1)).save(orderEntity);
    }

    @Test
    void updateOrderItens() {
        Long orderId = 1L;
        Map<Long, Integer> itensPedido = new HashMap<>();
        itensPedido.put(1L, 5);
        OrderEntity orderEntity = new OrderEntity(1L, new ArrayList<>());
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        when(orderItensRepository.save(any())).thenReturn(new OrderItensEntity());
        Order order = orderEntityMapper.toDomainObject(orderEntity);
        Order updatedOrder = orderRepositoryGateway.updateOrderItens(orderId, itensPedido);

        assertEquals(order, updatedOrder);
    }


    @Test
    void findOrders() {
        List<OrderEntity> ordersEntity = List.of(new OrderEntity(1L, new ArrayList<>()),
                new OrderEntity(2L, new ArrayList<>()));
        when(orderRepository.findAll()).thenReturn(ordersEntity);

        List<Order> ordersRecebido = orderRepositoryGateway.findOrders();

        assertEquals(ordersRecebido.size(), ordersEntity.size());
    }
}
