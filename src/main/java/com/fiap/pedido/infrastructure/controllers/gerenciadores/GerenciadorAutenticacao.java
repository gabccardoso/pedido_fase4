package com.fiap.pedido.infrastructure.controllers.gerenciadores;


import com.fiap.pedido.application.useCases.ClientInteractor;
import com.fiap.pedido.entities.Client;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTO;
import com.fiap.pedido.infrastructure.controllers.dto.ClientDTOMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class GerenciadorAutenticacao {

    private static final String SECRET_KEY_SESSAO = "humburguer_2024";
    private final ClientInteractor clientInteractor;
    private final ClientDTOMapper clientDTOMapper;

    public GerenciadorAutenticacao(ClientInteractor clientInteractor, ClientDTOMapper clientDTOMapper) {
        this.clientInteractor = clientInteractor;
        this.clientDTOMapper = clientDTOMapper;
    }

    public Long usuarioAutenticado(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY_SESSAO.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            if (claims.getExpiration().before(new Date())) throw new IllegalArgumentException("Token inválido");
            String cpf = claims.getSubject();
            String nome = claims.get("nome").toString();
            String email = claims.get("email").toString();
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setCpf(cpf);
            clientDTO.setEmail(email);
            clientDTO.setNome(nome);
            return verificaSeUsuarioExiste(clientDTO);
        } catch (Exception e) {
            throw e;
        }
    }

        public Long verificaSeUsuarioExiste(ClientDTO clientDTO){
            try {
                Client client = Optional.ofNullable(clientInteractor.findClientByCPF(clientDTO.getCpf()))
                        .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado para o CPF: " + clientDTO.getCpf()));
                return client.id();
            } catch (NoSuchElementException e) {
                Long newClientId = clientInteractor.createClient(clientDTOMapper.toClient(clientDTO));;
                return newClientId;
            }
    }

}
