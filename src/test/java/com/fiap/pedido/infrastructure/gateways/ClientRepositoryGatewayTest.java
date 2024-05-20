package com.fiap.pedido.infrastructure.gateways;


import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.persistence.ClientEntity;
import com.fiap.pedido.infrastructure.persistence.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientRepositoryGatewayTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientEntityMapper clientEntityMapper;

    private ClientRepositoryGateway clientRepositoryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientRepositoryGateway = new ClientRepositoryGateway(clientRepository, clientEntityMapper);
    }

    @Test
    void createClient() {
        Client client = new Client(1L, "Cliente 1", "cliente@email.com", "12345678910");
        ClientEntity clientEntity = new ClientEntity(/* provide necessary parameters */);
        when(clientEntityMapper.toEntity(client)).thenReturn(clientEntity);
        when(clientRepository.save(any())).thenReturn(clientEntity);

        Long clientId = clientRepositoryGateway.createClient(client);

        assertEquals(clientEntity.getId(), clientId);
        verify(clientRepository, times(1)).save(clientEntity);
    }

    @Test
    void findClientByCPF() {
        String cpf = "12345678910";
        ClientEntity clientEntity = new ClientEntity(1L, "Cliente 1", "cliente@email.com", "12345678910");

        when(clientRepository.findBycpf(cpf)).thenReturn(clientEntity);
        when(clientEntityMapper.toDomainObject(clientEntity)).thenCallRealMethod();

        Client client = new Client(1L, "Cliente 1", "cliente@email.com", "12345678910");
        Client foundClient = clientRepositoryGateway.findClientByCPF(cpf);

        assertEquals(client, foundClient);
    }

    @Test
    void findClients() {
        List<ClientEntity> clientEntities = List.of(new ClientEntity(/* provide necessary parameters */));
        when(clientRepository.findAll()).thenReturn(clientEntities);
        when(clientEntityMapper.toDomainObject(any())).thenCallRealMethod();

        List<Client> clients = clientRepositoryGateway.findClients();

        assertEquals(clientEntities.size(), clients.size());
    }
}
