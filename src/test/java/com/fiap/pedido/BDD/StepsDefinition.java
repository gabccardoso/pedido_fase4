package com.fiap.pedido.BDD;

import com.fiap.pedido.infrastructure.controllers.dto.OrderDTO;
import com.fiap.pedido.infrastructure.controllers.dto.ProductDTO;
import com.fiap.pedido.infrastructure.controllers.enums.Category;
import com.fiap.pedido.infrastructure.persistence.OrderEntity;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class StepsDefinition {

    private Response response;
    private OrderEntity orderResposta;
    private final String ENDIPOINT_API_ORDER = "http://localhost:5544";
    OrderDTO orderDTO = new OrderDTO(1L, new Date(), new ArrayList<>());
    @Quando("registrar um novo pedido")
    public void registrar_um_novo_pedido() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderDTO)
                .when()
                .post(ENDIPOINT_API_ORDER+"/pedido/criarPedido");
    }
    @Entao("o pedido é registrado com sucesso")
    public void o_pedido_é_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
    @Entao("é apresentado com sucesso")
    public void é_apresentado_com_sucesso() {
        response.then()
                .body(not(isEmptyString()));
    }

    @Dado("que já existe um pedido")
    public void que_já_existe_um_pedido() {

    }
    @Quando("buscar esse pedido")
    public void buscar_esse_pedido() {
        response = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get(ENDIPOINT_API_ORDER+"/pedido/buscarPedidos");
    }
    @Entao("o pedido retorna com sucesso")
    public void o_pedido_retorna_com_sucesso() {
        response.then().statusCode(200);
    }


    @Dado("existe um produto com aquele ID")
    public void existe_um_produto_com_aquele_id() {
        ProductDTO productDTO = new ProductDTO("hamburguer", Category.LANCHE, "BOMBOM", "image", 13.00, new ArrayList<>());
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(productDTO)
                .when()
                .post(ENDIPOINT_API_ORDER+"/produto/criarProduto");
    }
    @Quando("adicionar itens no pedido")
    public void adicionar_itens_no_pedido() {
        Long pedidoId = 1L;
        Map<Long, Integer> itensPedido = new HashMap<>();
        itensPedido.put(1L, 2);

        // Executar a solicitação POST para adicionar itens ao pedido
        response = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(itensPedido)
                    .when()
                    .post(ENDIPOINT_API_ORDER+"/pedido/adicionarItens/" + pedidoId);
    }
    @Entao("o pedido deve atualizar com sucesso")
    public void o_pedido_deve_atualizar_com_sucesso() {
        response.then()
                .statusCode(200)
                .body(not(empty()));
    }

}
