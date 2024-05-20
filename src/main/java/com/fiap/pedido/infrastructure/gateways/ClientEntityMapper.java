package com.fiap.pedido.infrastructure.gateways;


import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.persistence.ClientEntity;

public class ClientEntityMapper {
    ClientEntity toEntity(Client clientObjDomain){
        return new ClientEntity(clientObjDomain.id(), clientObjDomain.nome(), clientObjDomain.email(), clientObjDomain.cpf());
    }

    Client toDomainObject(ClientEntity clientEntity){
        return new Client(clientEntity.getId(), clientEntity.getNome(), clientEntity.getEmail(), clientEntity.getCpf());
    }
}
