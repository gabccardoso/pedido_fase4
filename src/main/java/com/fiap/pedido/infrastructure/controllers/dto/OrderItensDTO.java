package com.fiap.pedido.infrastructure.controllers.dto;

public class OrderItensDTO {
    Long orderId;
    Long productId;
    int quantity;

    public OrderItensDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItensDTO(Long productId, int quantity, Long orderId) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public OrderItensDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
