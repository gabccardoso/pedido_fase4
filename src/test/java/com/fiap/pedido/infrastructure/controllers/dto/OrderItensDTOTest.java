package com.fiap.pedido.infrastructure.controllers.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItensDTOTest {

    @Test
    void testConstructorAndGetters() {
        Long productId = 1L;
        int quantity = 5;
        Long orderId = 10L;

        OrderItensDTO orderItemDTO = new OrderItensDTO(productId, quantity, orderId);

        assertEquals(productId, orderItemDTO.getProductId());
        assertEquals(quantity, orderItemDTO.getQuantity());
        assertEquals(orderId, orderItemDTO.getOrderId());
    }

    @Test
    void testSetters() {
        OrderItensDTO orderItemDTO = new OrderItensDTO();

        Long productId = 1L;
        int quantity = 5;
        Long orderId = 10L;

        orderItemDTO.setProductId(productId);
        orderItemDTO.setQuantity(quantity);
        orderItemDTO.setOrderId(orderId);

        assertEquals(productId, orderItemDTO.getProductId());
        assertEquals(quantity, orderItemDTO.getQuantity());
        assertEquals(orderId, orderItemDTO.getOrderId());
    }
}