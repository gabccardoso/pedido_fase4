package com.fiap.pedido.application.gateways;

import com.fiap.pedido.entities.Order;

import java.util.List;
import java.util.Map;

public interface OrderGateway {
    Long createOrder(Order order);
    Order updateOrderItens(Long orderId, Map<Long, Integer> itensOrder);
    List<Order> findOrders();
}
