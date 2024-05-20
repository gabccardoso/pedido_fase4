package com.fiap.pedido.infrastructure.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientRepositoryTest {

    @Mock
    ClientRepository clientRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarCliente(){
        ClientEntity client = new ClientEntity(1L, "Cliente Teste", "cliente@teste.com", "12345678910");
        //Arrange
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(client);
        //Act
        ClientEntity clienteArmazenado = clientRepository.save(client);
        //Assert
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
        Assertions.assertEquals(clienteArmazenado, client);
    }

    @Test
    void devePermitirBuscarClientePorCPF(){
        ClientEntity client = new ClientEntity(1L, "Cliente Teste", "cliente@teste.com", "12345678910");
        //Arrange
        when(clientRepository.findBycpf(anyString())).thenReturn(client);
        //Act
        ClientEntity clienteRetornado = clientRepository.findBycpf(anyString());
        //Assert
        verify(clientRepository, times(1)).findBycpf(anyString());
        Assertions.assertEquals(clienteRetornado, client);
    }

    @Test
    void devePermitirDeletarCliente(){
        clientRepository.deleteById(any());
        verify(clientRepository, times(1)).deleteById(any());
    }

    @Test
    void devePermitirListarClientes(){
        ClientEntity cliente1 = new ClientEntity(1L, "Cliente1 Teste", "cliente1@teste.com", "12345678910");
        ClientEntity cliente2 = new ClientEntity(2L, "Cliente2 Teste", "cliente2@teste.com", "12345678911");
        List<ClientEntity> clientes = Arrays.asList(cliente1, cliente2);

        when(clientRepository.findAll()).thenReturn(clientes);

        List<ClientEntity> clientesRetornados = clientRepository.findAll();

        verify(clientRepository, times(1)).findAll();
        Assertions.assertEquals(clientes, clientesRetornados);

    }
}
