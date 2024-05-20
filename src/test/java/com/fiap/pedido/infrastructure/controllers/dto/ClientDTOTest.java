package com.fiap.pedido.infrastructure.controllers.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientDTOTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String nome = "John Doe";
        String email = "johndoe@example.com";
        String cpf = "12345678900";

        ClientDTO clientDTO = new ClientDTO(id, nome, email, cpf);

        assertEquals(id, clientDTO.getId());
        assertEquals(nome, clientDTO.getNome());
        assertEquals(email, clientDTO.getEmail());
        assertEquals(cpf, clientDTO.getCpf());
    }

    @Test
    void testSetters() {
        ClientDTO clientDTO = new ClientDTO();

        Long id = 1L;
        String nome = "John Doe";
        String email = "johndoe@example.com";
        String cpf = "12345678900";

        clientDTO.setId(id);
        clientDTO.setNome(nome);
        clientDTO.setEmail(email);
        clientDTO.setCpf(cpf);

        assertEquals(id, clientDTO.getId());
        assertEquals(nome, clientDTO.getNome());
        assertEquals(email, clientDTO.getEmail());
        assertEquals(cpf, clientDTO.getCpf());
    }
}