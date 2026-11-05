package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ProductControllerRA {

    private String clientUsername, clientPassword, adminUsername, adminPassword;
    private String clientToken, adminToken, invalidToken;
    private Long existingProductId, nonExistingProductId, dependentProductId;
    private String productName;

    private Map<String, Object> postProductInstance;

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:8080";

        clientUsername = "maria@gmail.com";
        clientPassword = "123456";

        adminUsername = "alex@gmail.com";
        adminPassword = "123456";

        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
        invalidToken = adminToken + "xpto";

        productName = "Macbook";

        postProductInstance = new HashMap<>();
        postProductInstance.put("name", "Meu Produto");
        postProductInstance.put("description", "Descrição do meu produto");
        postProductInstance.put("price", 50.0f);
        postProductInstance.put("imgUrl", "https://example.com/image.jpg");

        List<Map<String, Object>> categories = new ArrayList<>();

        Map<String, Object> category1 = new HashMap<>();
        category1.put("id", 2);

        Map<String, Object> category2 = new HashMap<>();
        category2.put("id", 3);

        categories.add(category1);
        categories.add(category2);

        postProductInstance.put("categories", categories);
    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() {
        existingProductId = 2L;

        given()
                .get("/products/{id}", existingProductId)
        .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("name", is("Smart TV"))
                .body("imgUrl", is("https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg"))
                .body("price", is(2190.0f));
    }

    @Test
    public void findAllShouldReturnPageProductsWhenProductNameIsEmpty() {
        given()
                .get("/products?page=0")
        .then()
                .statusCode(200)
                .body("content.name", hasItems("Macbook Pro", "PC Gamer Tera"));
    }

    @Test
    public void findAllShouldReturnPageProductsWhenProductNameIsNotEmpty() {
        given()
                .get("/products?name={productName}", productName)
        .then()
                .statusCode(200)
                .body("content.id[0]", is(3))
                .body("content.name[0]", equalTo("Macbook Pro"))
                .body("content.price[0]", is(1250.0f))
                .body("content.imgUrl[0]", equalTo("https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg"));
    }

    @Test
    public void findAllShouldReturnPagedProductsWithPriceGreaterThan2000() {
        given()
                .get("/products?size=25")
        .then()
                .statusCode(200)
                .body("content.findAll { it.price > 2000 }.name", hasItems("Smart TV", "PC Gamer Weed"));
    }

    @Test
    public void insertShouldReturnCreatedWhenAdminLogged() {
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post("/products")
        .then()
                .statusCode(201)
                .body("name", equalTo("Meu Produto"))
                .body("price", is(50.0f))
                .body("imgUrl", equalTo("https://example.com/image.jpg"))
                .body("categories.id", hasItems(2, 3));
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndInvalidName() {
        postProductInstance.put("name", "ab");
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post("/products")
        .then()
                .statusCode(422)
                .body("errors.message[0]", equalTo("Nome precisa ter de 3 a 80 caracteres!"));
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndInvalidDescription() {
        postProductInstance.put("description", "ab");
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/products")
            .then()
                .statusCode(422)
                .body("errors.message[0]", equalTo("Descrição precisa ter no minimo 10 caracteres!"));
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndPriceIsNegative() {
        postProductInstance.put("price", -50.0f);
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/products")
            .then()
                .statusCode(422)
                .body("errors.message[0]", equalTo("O preço deve ser positivo!"));
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndPriceIszero() {
        postProductInstance.put("price", 0.0f);
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/products")
            .then()
                .statusCode(422)
                .body("errors.message[0]", equalTo("O preço deve ser positivo!"));
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndProductHasNoCategory() {
        postProductInstance.put("categories", null);
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/products")
            .then()
                .statusCode(422)
                .body("errors.message[0]", equalTo("Deve ter ao menos uma categoria!"));
    }

    @Test
    public void insertShouldReturnForbbidenWhenClientLogged() {
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + clientToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/products")
            .then()
                .statusCode(403);
    }

    @Test
    public void insertShouldReturnUnauthotizedWhenInvalidToen() {
        JSONObject newProduct = new JSONObject(postProductInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + invalidToken)
                .body(newProduct)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/products")
            .then()
                .statusCode(401);
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExistAndAdminLogged() {
        nonExistingProductId = 100L;

        given()
                .header("Authorization", "Bearer " + adminToken)
            .when()
                .delete("/products/{id}", nonExistingProductId)
            .then()
                .statusCode(404)
                .body("error", equalTo("Recurso não encontrado!"))
                .body("status", equalTo(404));
    }

    @Test
    public void deleteShouldReturnBadRequestWhenDependentIdAndAdminLogged() {
        dependentProductId = 3L;

        given()
                .header("Authorization", "Bearer " + adminToken)
            .when()
                .delete("/products/{id}", dependentProductId)
            .then()
                .statusCode(400);
    }

    @Test
    public void deleteShouldReturnForbiddenWhenClientLogged() {
        existingProductId = 25L;

        given()
                .header("Authorization", "Bearer " + clientToken)
            .when()
                .delete("/products/{id}", existingProductId)
            .then()
                .statusCode(403);
    }

    @Test
    public void deleteShouldReturnUnauthorizedWhenInvalidToken() {
        existingProductId = 25L;

        given()
                .header("Authorization", "Bearer " + invalidToken)
            .when()
                .delete("/products/{id}", existingProductId)
            .then()
                .statusCode(401);
    }
}
