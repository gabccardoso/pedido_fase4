package com.fiap.pedido.infrastructure.controllers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean_client.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ClientControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @Sql(scripts = {"/clean_client.sql", "/data_client.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void devePermitirBuscarClientePorCPF(){
        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("cpf", "12345678910")
                .when()
                .get("/cliente/buscaPorCPF")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @Sql(scripts = {"/clean_client.sql", "/data_client.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void devePermitirBuscarClientes(){
        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/cliente/buscaClientes")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
