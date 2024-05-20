package com.fiap.pedido.infrastructure.controllers.dto;


import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long clientId;
    private Date dataCriacao;
    private List<OrderItensDTO> orderItens;

    public OrderDTO(Long clientId, Date dataCriacao, List<OrderItensDTO> orderItens) {
        this.clientId = clientId;
        this.dataCriacao = dataCriacao;
        this.orderItens = orderItens;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<OrderItensDTO> getOrderItens() {
        return orderItens;
    }

    public void setOrderItens(List<OrderItensDTO> orderItens) {
        this.orderItens = orderItens;
    }
}
