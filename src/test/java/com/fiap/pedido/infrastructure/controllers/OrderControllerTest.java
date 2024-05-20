package com.fiap.pedido.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.fiap.pedido.application.useCases.OrderInteractor;
import com.fiap.pedido.entities.Order;
import com.fiap.pedido.infrastructure.controllers.dto.OrderDTO;
import com.fiap.pedido.infrastructure.controllers.dto.OrderDTOMapper;
import com.fiap.pedido.infrastructure.controllers.gerenciadores.GerenciadorAutenticacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class OrderControllerTest {

    @Mock
    private OrderInteractor orderInteractor;

    @Mock
    private OrderDTOMapper orderDTOMapper;

    @Mock
    private GerenciadorAutenticacao gerenciadorAutenticacao;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testBuscarPedidos() {
        // Mocking
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(1L, new Date(),new ArrayList<>());
        Order order2 = new Order(2L, new Date(),new ArrayList<>());
        orders.add(order1);
        orders.add(order2);

        List<OrderDTO> ordersDTO = new ArrayList<>();
        ordersDTO.add(new OrderDTO(1L, new Date(),new ArrayList<>()));
        ordersDTO.add(new OrderDTO(2L, new Date(),new ArrayList<>()));

        when(orderInteractor.findOrders()).thenReturn(orders);
        when(orderDTOMapper.toOrderDTO(order1)).thenReturn(ordersDTO.get(0));
        when(orderDTOMapper.toOrderDTO(order2)).thenReturn(ordersDTO.get(1));

        // Testing
        ResponseEntity<List<OrderDTO>> responseEntity = orderController.buscarPedidos();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ordersDTO, responseEntity.getBody());
    }

    @Test
    void testCriarPedido() {
        // Mocking
        OrderDTO orderDTO = new OrderDTO(1L, new Date(),new ArrayList<>());
        orderDTO.setClientId(1L);

        Order order = new Order(1L, new Date(),new ArrayList<>());
        Long orderId = 1L;

        when(gerenciadorAutenticacao.usuarioAutenticado(anyString())).thenReturn(1L);
        when(orderDTOMapper.toOrder(orderDTO)).thenReturn(order);
        when(orderInteractor.createOrder(order)).thenReturn(orderId);

        // Testing
        ResponseEntity<Long> responseEntity = orderController.criarPedido(orderDTO, "token");

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderId, responseEntity.getBody());
    }

    @Test
    void testAdicionarItensAoPedido() {
        // Mocking
        Long pedidoId = 1L;
        Map<Long, Integer> itensPedido = new HashMap<>();
        itensPedido.put(1L, 2);

        Order order = new Order(1L, new Date(),new ArrayList<>());

        when(orderInteractor.updateOrderItens(pedidoId, itensPedido)).thenReturn(order);
        when(orderDTOMapper.toOrderDTO(order)).thenReturn(new OrderDTO(1L, new Date(),new ArrayList<>()));

        // Testing
        ResponseEntity<OrderDTO> responseEntity = orderController.adicionarItensAoPedido(pedidoId, itensPedido);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}