package com.fiap.pedido.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItensRepository extends JpaRepository<OrderItensEntity, Long> {
}
