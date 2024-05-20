package com.fiap.pedido.infrastructure.controllers;

import com.fiap.pedido.application.useCases.ClientInteractor;
import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTO;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClientController {

    private final ClientInteractor clientInteractor;
    private final ClientDTOMapper clientDTOMapper;

    public ClientController(ClientInteractor clientInteractor, ClientDTOMapper clientDTOMapper) {
        this.clientInteractor = clientInteractor;
        this.clientDTOMapper = clientDTOMapper;
    }

    @GetMapping(value= "/buscaPorCPF")
    public ResponseEntity<ClientDTO> findClientByCPF(@RequestParam String cpf){
        Client client = clientInteractor.findClientByCPF(cpf);
        return ResponseEntity.ok(clientDTOMapper.toClientDTO(client));
    }

    @GetMapping(value= "/buscaClientes")
    public ResponseEntity<List<ClientDTO>> findClient(){
        List<Client> clients = clientInteractor.findClients();
        List<ClientDTO> clientsDTO = new ArrayList<>();
        for(Client client:clients){
            clientsDTO.add(clientDTOMapper.toClientDTO(client));
        }
        return ResponseEntity.ok(clientsDTO);
    }

}
