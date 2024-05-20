package com.fiap.pedido.application.useCases;

import com.fiap.pedido.application.gateways.ClientGateway;
import com.fiap.pedido.entities.Client;

import java.util.List;

public class ClientInteractor {

    private ClientGateway clientGateway;

    public ClientInteractor(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    public Long createClient(Client client){
        return clientGateway.createClient(client);
    }

    public Client findClientByCPF(String cpf){
        return clientGateway.findClientByCPF(cpf);
    }

    public List<Client> findClients(){
        return clientGateway.findClients();
    }
}
