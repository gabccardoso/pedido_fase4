package com.fiap.pedido.application.gateways;

import com.fiap.pedido.entities.Client;

import java.util.List;

public interface ClientGateway {
    Long createClient(Client client);
    Client findClientByCPF(String cpf);
    List<Client> findClients();
}
