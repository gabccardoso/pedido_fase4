package com.fiap.pedido.main;


import com.fiap.pedido.application.gateways.ClientGateway;
import com.fiap.pedido.application.useCases.ClientInteractor;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTOMapper;
import com.fiap.pedido.infrastructure.gateways.ClientEntityMapper;
import com.fiap.pedido.infrastructure.gateways.ClientRepositoryGateway;
import com.fiap.pedido.infrastructure.persistence.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    ClientInteractor clientInteractor(ClientGateway clientGateway){
        return new ClientInteractor(clientGateway);
    }

    @Bean
    ClientGateway clientGateway(ClientRepository clientRepository, ClientEntityMapper clientEntityMapper){
        return new ClientRepositoryGateway(clientRepository, clientEntityMapper);
    }

    @Bean
    ClientEntityMapper clientEntityMapper(){
        return new ClientEntityMapper();
    }

    @Bean
    ClientDTOMapper clientDTOMapper(){
        return new ClientDTOMapper();
    }
}
