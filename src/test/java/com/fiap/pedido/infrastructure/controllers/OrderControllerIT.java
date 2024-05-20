package com.fiap.pedido.infrastructure.controllers;


import com.fiap.pedido.infrastructure.controllers.dto.OrderDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean_order.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @Sql(scripts = {"/data_order.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void devePermitirBuscarPedidos(){
        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/pedido/buscarPedidos")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void devePermitirCriarPedidos(){
        OrderDTO orderDTO = new OrderDTO(10L, new Date(), new ArrayList<>());
        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderDTO)
                .when()
                .post("/pedido/criarPedido")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @Sql(scripts = { "/data_order.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void devePermitirAdicionarItens(){
        long pedidoId = 1L;
        Map<Long, Integer> itensPedido = new HashMap<>();
        itensPedido.put(1L, 2); // Exemplo de item com ID 1001 e quantidade 2

        // Executar a solicitação POST para adicionar itens ao pedido
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(itensPedido)
                .when()
                .post("/pedido/adicionarItens/" + pedidoId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
