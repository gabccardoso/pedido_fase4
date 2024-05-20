package com.fiap.pedido.infrastructure.controllers;

import com.fiap.pedido.application.useCases.OrderInteractor;
import com.fiap.pedido.entities.Order;
import com.fiap.pedido.infrastructure.controllers.dto.OrderDTO;
import com.fiap.pedido.infrastructure.controllers.dto.OrderDTOMapper;
import com.fiap.pedido.infrastructure.controllers.gerenciadores.GerenciadorAutenticacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pedido")
public class OrderController {

    private final OrderInteractor orderInteractor;
    private final OrderDTOMapper orderDTOMapper;
    private final GerenciadorAutenticacao gerenciadorAutenticacao;

    public OrderController(OrderInteractor orderInteractor, OrderDTOMapper orderDTOMapper, GerenciadorAutenticacao gerenciadorAutenticacao) {
        this.orderInteractor = orderInteractor;
        this.orderDTOMapper = orderDTOMapper;
        this.gerenciadorAutenticacao = gerenciadorAutenticacao;
    }

    @GetMapping(value = "buscarPedidos")
    public ResponseEntity<List<OrderDTO>> buscarPedidos(){
        List<Order> orders = orderInteractor.findOrders();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        for(Order order : orders){
            ordersDTO.add(orderDTOMapper.toOrderDTO(order));
        }
        return ResponseEntity.ok(ordersDTO);
    }

    @PostMapping(value = "criarPedido")
    public ResponseEntity<Long> criarPedido(@RequestBody OrderDTO orderDTO, @RequestHeader(value = "token", required = false) String token){
        Long clientId = token != null ? gerenciadorAutenticacao.usuarioAutenticado(token) : null;
        orderDTO.setClientId(clientId);
        Long orderId = orderInteractor.createOrder(orderDTOMapper.toOrder(orderDTO));
        return ResponseEntity.ok(orderId);
    }

    @PostMapping(value = "adicionarItens/{pedidoId}")
    public ResponseEntity<OrderDTO> adicionarItensAoPedido(@PathVariable Long pedidoId, @RequestBody Map<Long, Integer> itensPedido) {
        Order order = orderInteractor.updateOrderItens(pedidoId, itensPedido);
        return ResponseEntity.ok(orderDTOMapper.toOrderDTO(order));
    }
}
