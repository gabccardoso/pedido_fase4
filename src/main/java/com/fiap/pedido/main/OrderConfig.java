package com.fiap.pedido.main;

import com.fiap.pedido.application.gateways.OrderGateway;
import com.fiap.pedido.application.useCases.OrderInteractor;
import com.fiap.pedido.infrastructure.controllers.dto.OrderDTOMapper;
import com.fiap.pedido.infrastructure.gateways.OrderEntityMapper;
import com.fiap.pedido.infrastructure.gateways.OrderRepositoryGateway;
import com.fiap.pedido.infrastructure.persistence.OrderItensRepository;
import com.fiap.pedido.infrastructure.persistence.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    OrderInteractor orderInteractor(OrderGateway orderGateway){
        return new OrderInteractor(orderGateway);
    }

    @Bean
    OrderGateway orderGateway(OrderRepository orderRepository, OrderItensRepository orderItensRepository, OrderEntityMapper orderEntityMapper){
        return new OrderRepositoryGateway(orderRepository, orderItensRepository, orderEntityMapper);
    }

    @Bean
    OrderEntityMapper orderEntityMapper(){
        return new OrderEntityMapper();
    }

    @Bean
    OrderDTOMapper orderDTOMapper(){
        return new OrderDTOMapper();
    }
}
