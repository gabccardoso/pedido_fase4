package com.fiap.pedido.application.useCases;



import com.fiap.pedido.application.gateways.OrderGateway;
import com.fiap.pedido.entities.Order;

import java.util.List;
import java.util.Map;

public class OrderInteractor {

    private final OrderGateway orderGateway;

    public OrderInteractor(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Long createOrder(Order order){
        return orderGateway.createOrder(order);
    }
    public Order updateOrderItens(Long orderId, Map<Long, Integer> itensOrder){
        return orderGateway.updateOrderItens(orderId, itensOrder);
    }
    public List<Order> findOrders(){
        return orderGateway.findOrders();
    }
}
