package com.fiap.pedido.entities;


import java.util.Date;
import java.util.List;

public record Order(Long clientId, Date dataCriacao, List<OrderItens> orderItens) {

}
