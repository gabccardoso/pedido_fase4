package com.fiap.pedido.infrastructure.controllers;

import com.fiap.pedido.application.useCases.ClientInteractor;
import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.controllers.ClientController;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTO;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClientControllerTest {

    @Mock
    private ClientInteractor clientInteractor;

    @Mock
    private ClientDTOMapper clientDTOMapper;

    private ClientController clientController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        clientController = new ClientController(clientInteractor, clientDTOMapper);
    }

    @Test
    public void testFindClientByCPF() {
        // Mock data
        String cpf = "123456789";
        Client client = new Client(1L, "Usuario 1", "usuario1@teste.com", "12345678910");


        // Mock interactor
        when(clientInteractor.findClientByCPF(cpf)).thenReturn(client);

        // Mock DTO mapper
        ClientDTO clientDTO = new ClientDTO();
        when(clientDTOMapper.toClientDTO(client)).thenReturn(clientDTO);

        // Call controller method
        ResponseEntity<ClientDTO> responseEntity = clientController.findClientByCPF(cpf);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientDTO, responseEntity.getBody());
    }

    @Test
    public void testFindClient() {
        // Mock data
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "Usuario 1", "usuario1@teste.com", "12345678910"));
        clients.add(new Client(2L, "Usuario 2", "usuario2@teste.com", "12345678911"));

        // Mock interactor
        when(clientInteractor.findClients()).thenReturn(clients);

        // Mock DTO mapper
        List<ClientDTO> clientsDTO = new ArrayList<>();
        clientsDTO.add(new ClientDTO());
        clientsDTO.add(new ClientDTO());
        when(clientDTOMapper.toClientDTO(clients.get(0))).thenReturn(clientsDTO.get(0));
        when(clientDTOMapper.toClientDTO(clients.get(1))).thenReturn(clientsDTO.get(1));

        // Call controller method
        ResponseEntity<List<ClientDTO>> responseEntity = clientController.findClient();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientsDTO, responseEntity.getBody());
    }
}
