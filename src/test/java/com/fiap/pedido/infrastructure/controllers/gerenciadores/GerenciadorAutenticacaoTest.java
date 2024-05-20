package com.fiap.pedido.infrastructure.controllers.gerenciadores;

import com.fiap.pedido.application.useCases.ClientInteractor;
import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTO;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTOMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class GerenciadorAutenticacaoTest {

    @Mock
    private ClientInteractor clientInteractor;

    @Mock
    private ClientDTOMapper clientDTOMapper;

    @InjectMocks
    private GerenciadorAutenticacao gerenciadorAutenticacao;

    private String validToken;
    private String expiredToken;
    private Claims claims;

    private static final String SECRET_KEY_SESSAO = "humburguer_2024";

    @BeforeEach
    void setUp() {
        // Generate a valid token for testing
        claims = Jwts.claims().setSubject("12345678900");
        claims.put("nome", "John Doe");
        claims.put("email", "johndoe@example.com");

        validToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 100000)) // Valid for a short time
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY_SESSAO.getBytes())
                .compact();

        expiredToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() - 1000)) // Already expired
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY_SESSAO.getBytes())
                .compact();
    }

    @Test
    void testUsuarioAutenticadoValidToken() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCpf("12345678900");
        clientDTO.setNome("John Doe");
        clientDTO.setEmail("johndoe@example.com");

        Client client = mock(Client.class);
        when(client.id()).thenReturn(1L);
        when(clientInteractor.findClientByCPF("12345678900")).thenReturn(client);

        Long clientId = gerenciadorAutenticacao.usuarioAutenticado(validToken);

        assertNotNull(clientId);
        assertEquals(1L, clientId);
    }

    @Test
    void testUsuarioAutenticadoExpiredToken() {
        Exception exception = assertThrows(ExpiredJwtException.class, () -> {
            gerenciadorAutenticacao.usuarioAutenticado(expiredToken);
        });
    }

    @Test
    void testUsuarioAutenticadoNewClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCpf("12345678900");
        clientDTO.setNome("John Doe");
        clientDTO.setEmail("johndoe@example.com");

        when(clientInteractor.findClientByCPF("12345678900")).thenThrow(new NoSuchElementException());
        when(clientDTOMapper.toClient(any(ClientDTO.class))).thenReturn(new Client(1L, "John Doe", "johndoe@example.com", "12345678900"));
        when(clientInteractor.createClient(any(Client.class))).thenReturn(2L);

        Long clientId = gerenciadorAutenticacao.usuarioAutenticado(validToken);

        assertNotNull(clientId);
        assertEquals(2L, clientId);
    }

    @Test
    void testVerificaSeUsuarioExisteExistingClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCpf("12345678900");
        clientDTO.setNome("John Doe");
        clientDTO.setEmail("johndoe@example.com");

        Client client = mock(Client.class);
        when(client.id()).thenReturn(1L);
        when(clientInteractor.findClientByCPF("12345678900")).thenReturn(client);

        Long clientId = gerenciadorAutenticacao.verificaSeUsuarioExiste(clientDTO);

        assertNotNull(clientId);
        assertEquals(1L, clientId);
    }

    @Test
    void testVerificaSeUsuarioExisteNewClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCpf("12345678900");
        clientDTO.setNome("John Doe");
        clientDTO.setEmail("johndoe@example.com");

        when(clientInteractor.findClientByCPF("12345678900")).thenThrow(new NoSuchElementException());
        when(clientDTOMapper.toClient(any(ClientDTO.class))).thenReturn(new Client(1L, "John Doe", "johndoe@example.com", "12345678900"));
        when(clientInteractor.createClient(any(Client.class))).thenReturn(2L);

        Long clientId = gerenciadorAutenticacao.verificaSeUsuarioExiste(clientDTO);

        assertNotNull(clientId);
        assertEquals(2L, clientId);
    }

}
