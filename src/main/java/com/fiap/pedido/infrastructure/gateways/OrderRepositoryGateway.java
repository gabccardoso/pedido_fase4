package com.fiap.pedido.infrastructure.gateways;


import com.fiap.pedido.application.gateways.OrderGateway;
import com.fiap.pedido.entities.Order;
import com.fiap.pedido.infrastructure.persistence.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderRepositoryGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderItensRepository orderItensRepository;
    private final OrderEntityMapper orderEntityMapper;

    public OrderRepositoryGateway(OrderRepository orderRepository, OrderItensRepository orderItensRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.orderItensRepository = orderItensRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public Long createOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        OrderEntity orderSaved = orderRepository.save(orderEntity);
        return orderSaved.getId();
    }

    @Override
    public Order updateOrderItens(Long orderId, Map<Long, Integer> itensPedido) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        for (Map.Entry<Long, Integer> entry : itensPedido.entrySet()) {
            int quantity = entry.getValue();

            OrderItensEntity orderItensEntity = new OrderItensEntity();
            orderItensEntity.setProductId(entry.getKey());
            orderItensEntity.setQuantity(quantity);
            orderItensEntity.setOrderId(orderEntity.getId());

            orderEntity.getItens().add(orderItensEntity);
            orderItensRepository.save(orderItensEntity);
        }

        orderRepository.save(orderEntity);
        return orderEntityMapper.toDomainObject(orderEntity);
    }


    @Override
    public List<Order> findOrders() {
        List<OrderEntity> ordersEntity = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        for(OrderEntity orderEntity : ordersEntity){
            orders.add(orderEntityMapper.toDomainObject(orderEntity));
        }
        return orders;
    }
}
