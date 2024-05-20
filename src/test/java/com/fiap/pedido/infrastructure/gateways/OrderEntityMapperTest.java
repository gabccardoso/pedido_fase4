package com.fiap.pedido.infrastructure.gateways;


import com.fiap.pedido.entities.Order;
import com.fiap.pedido.entities.OrderItens;
import com.fiap.pedido.infrastructure.persistence.OrderEntity;
import com.fiap.pedido.infrastructure.persistence.OrderItensEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderEntityMapperTest {

    private final OrderEntityMapper mapper = new OrderEntityMapper();

    @Test
    void toEntity_DeveMapearOrderParaOrderEntity() {
        // Arrange
        OrderItens orderItem = new OrderItens(1L, 2, 1L);
        List<OrderItens> orderItensList = new ArrayList<>();
        orderItensList.add(orderItem);

        Order order = new Order(1L, new Date(), orderItensList);

        // Act
        OrderEntity orderEntity = mapper.toEntity(order);

        // Assert
        assertEquals(order.clientId(), orderEntity.getClientId());
        assertEquals(order.orderItens().size(), orderEntity.getItens().size());
        assertEquals(order.orderItens().get(0).product(), orderEntity.getItens().get(0).getProductId());
        assertEquals(order.orderItens().get(0).quantity(), orderEntity.getItens().get(0).getQuantity());
    }

    @Test
    void toDomainObject_DeveMapearOrderEntityParaOrder() {
        // Arrange
        OrderItensEntity orderItemEntity = new OrderItensEntity(1L, 2, 1L);
        List<OrderItensEntity> orderItensEntityList = new ArrayList<>();
        orderItensEntityList.add(orderItemEntity);

        OrderEntity orderEntity = new OrderEntity(1L, orderItensEntityList);

        // Act
        Order order = mapper.toDomainObject(orderEntity);

        // Assert
        assertEquals(orderEntity.getClientId(), order.clientId());
        assertEquals(orderEntity.getItens().size(), order.orderItens().size());
        assertEquals(orderEntity.getItens().get(0).getProductId(), order.orderItens().get(0).product());
        assertEquals(orderEntity.getItens().get(0).getQuantity(), order.orderItens().get(0).quantity());
    }

    @Test
    void toOrderItensList_DeveMapearOrderItensEntityListParaOrderItensList() {
        // Arrange
        List<OrderItensEntity> orderItensEntityList = new ArrayList<>();
        orderItensEntityList.add(new OrderItensEntity(1L, 2, 1L));
        orderItensEntityList.add(new OrderItensEntity(2L, 3, 2L));

        // Act
        List<OrderItens> orderItensList = mapper.toOrderItensList(orderItensEntityList);

        // Assert
        assertEquals(orderItensEntityList.size(), orderItensList.size());
        assertEquals(orderItensEntityList.get(0).getProductId(), orderItensList.get(0).product());
        assertEquals(orderItensEntityList.get(0).getQuantity(), orderItensList.get(0).quantity());
        assertEquals(orderItensEntityList.get(1).getProductId(), orderItensList.get(1).product());
        assertEquals(orderItensEntityList.get(1).getQuantity(), orderItensList.get(1).quantity());
    }

    @Test
    void toOrderItensListEntity_DeveMapearOrderItensListParaOrderItensEntityList() {
        // Arrange
        List<OrderItens> orderItensList = new ArrayList<>();
        orderItensList.add(new OrderItens(1L, 2, 1L));
        orderItensList.add(new OrderItens(2L, 3, 1L));

        // Act
        List<OrderItensEntity> orderItensEntityList = mapper.toOrderItensListEntity(orderItensList);

        // Assert
        assertEquals(orderItensList.size(), orderItensEntityList.size());
        assertEquals(orderItensList.get(0).product(), orderItensEntityList.get(0).getProductId());
        assertEquals(orderItensList.get(0).quantity(), orderItensEntityList.get(0).getQuantity());
        assertEquals(orderItensList.get(1).product(), orderItensEntityList.get(1).getProductId());
        assertEquals(orderItensList.get(1).quantity(), orderItensEntityList.get(1).getQuantity());
    }
}
