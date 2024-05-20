package com.fiap.pedido.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findBycpf(String cpf);
}
