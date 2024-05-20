package com.fiap.pedido.infrastructure.gateways;

import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.persistence.ClientEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientEntityMapperTest {

    private final ClientEntityMapper mapper = new ClientEntityMapper();

    @Test
    void toEntity_DeveMapearClientParaClientEntity() {
        // Arrange
        Client client = new Client(1L, "John Doe", "john@example.com", "12345678901");

        // Act
        ClientEntity clientEntity = mapper.toEntity(client);

        // Assert
        assertEquals(client.id(), clientEntity.getId());
        assertEquals(client.nome(), clientEntity.getNome());
        assertEquals(client.email(), clientEntity.getEmail());
        assertEquals(client.cpf(), clientEntity.getCpf());
    }

    @Test
    void toDomainObject_DeveMapearClientEntityParaClient() {
        // Arrange
        ClientEntity clientEntity = new ClientEntity(1L, "John Doe", "john@example.com", "12345678901");

        // Act
        Client client = mapper.toDomainObject(clientEntity);

        // Assert
        assertEquals(clientEntity.getId(), client.id());
        assertEquals(clientEntity.getNome(), client.nome());
        assertEquals(clientEntity.getEmail(), client.email());
        assertEquals(clientEntity.getCpf(), client.cpf());
    }
}
