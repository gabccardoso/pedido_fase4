package com.fiap.pedido.infrastructure.controllers.dto;


import com.fiap.pedido.entities.Client;

public class ClientDTOMapper {
    public ClientDTO toClientDTO(Client client){
        return new ClientDTO(client.id(), client.nome(), client.email(), client.cpf());
    }

    public Client toClient(ClientDTO clientDTO){
        return new Client(clientDTO.getId(), clientDTO.getNome(), clientDTO.getEmail(), clientDTO.getCpf());
    }
}
