package com.fiap.pedido.infrastructure.gateways;

import com.fiap.pedido.entities.Order;
import com.fiap.pedido.entities.OrderItens;
import com.fiap.pedido.infrastructure.persistence.OrderEntity;
import com.fiap.pedido.infrastructure.persistence.OrderItensEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderEntityMapper {
    public OrderEntity toEntity(Order orderObjDomain){
        return new OrderEntity(orderObjDomain.clientId(), toOrderItensListEntity(orderObjDomain.orderItens()));
    }

    public Order toDomainObject(OrderEntity orderEntity){
        return new Order(orderEntity.getClientId(), orderEntity.getDataCriacao(), toOrderItensList(orderEntity.getItens()));
    }

    public List<OrderItens> toOrderItensList(List<OrderItensEntity> orderItensEntities) {
        List<OrderItens> orderItensList = new ArrayList<>();
        for (OrderItensEntity orderItensEntity : orderItensEntities) {
            orderItensList.add(new OrderItens(orderItensEntity.getProductId(), orderItensEntity.getQuantity(), orderItensEntity.getOrderId()));
        }
        return orderItensList;
    }

    public List<OrderItensEntity> toOrderItensListEntity(List<OrderItens> orderItens) {
        List<OrderItensEntity> orderItensEntityList = new ArrayList<>();
        for (OrderItens orderItem : orderItens) {
            orderItensEntityList.add(new OrderItensEntity(orderItem.product(), orderItem.quantity(), orderItem.order()));
        }
        return orderItensEntityList;
    }
}
