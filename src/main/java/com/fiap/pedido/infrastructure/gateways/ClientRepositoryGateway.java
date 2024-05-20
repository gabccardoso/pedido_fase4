package com.fiap.pedido.infrastructure.gateways;

import com.fiap.pedido.application.gateways.ClientGateway;
import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.persistence.ClientEntity;
import com.fiap.pedido.infrastructure.persistence.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClientRepositoryGateway implements ClientGateway {

    private final ClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    public ClientRepositoryGateway(ClientRepository clientRepository, ClientEntityMapper clientEntityMapper) {
        this.clientRepository = clientRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Long createClient(Client clientDomainObj) {
        ClientEntity clientEntity = clientEntityMapper.toEntity(clientDomainObj);
        ClientEntity clientSaved = clientRepository.save(clientEntity);
        return clientSaved.getId();
    }

    @Override
    public Client findClientByCPF(String cpf) {
        ClientEntity clientEntity = Optional.ofNullable(clientRepository.findBycpf(cpf))
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado para o CPF: " + cpf));
        return clientEntityMapper.toDomainObject(clientEntity);
    }

    @Override
    public List<Client> findClients() {
        List<ClientEntity> clientEntity = clientRepository.findAll();
        List<Client> clients = new ArrayList<>();
        for(ClientEntity client:clientEntity){
            clients.add(clientEntityMapper.toDomainObject(client));
        }
        return clients;
    }
}
