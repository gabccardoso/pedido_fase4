package com.fiap.pedido.main;

import com.fiap.pedido.application.gateways.ProductGateway;
import com.fiap.pedido.application.useCases.ProductInteractor;
import com.fiap.pedido.infrastructure.controllers.dto.ProductDTOMapper;
import com.fiap.pedido.infrastructure.gateways.ProductEntityMapper;
import com.fiap.pedido.infrastructure.gateways.ProductRepositoryGateway;
import com.fiap.pedido.infrastructure.persistence.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    ProductInteractor productInteractor(ProductGateway productGateway){
        return new ProductInteractor(productGateway);
    }

    @Bean
    ProductGateway productGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper){
        return new ProductRepositoryGateway(productRepository, productEntityMapper);
    }

    @Bean
    ProductEntityMapper productEntityMapper(){
        return new ProductEntityMapper();
    }

    @Bean
    ProductDTOMapper productDTOMapper(){
        return new ProductDTOMapper();
    }
}
