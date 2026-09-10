package com.iscram.codesphere.adapter.in.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@io.quarkus.test.junit.QuarkusTest
class FruitResourceTest {

    @Test
    void testFruitCrudLifecycle() {
        String id = given()
                .contentType("application/json")
                .body("""
                        {
                          "name": "Apple",
                          "description": "Crisp"
                        }
                        """)
                .when()
                .post("/fruits")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Apple"))
                .body("description", equalTo("Crisp"))
                .extract()
                .path("id");

        given()
                .when()
                .get("/fruits/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Apple"))
                .body("description", equalTo("Crisp"));

        given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .body("[0].id", equalTo(id))
                .body("[0].name", equalTo("Apple"))
                .body("[0].description", equalTo("Crisp"));

        given()
                .when()
                .delete("/fruits/" + id)
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/fruits/" + id)
                .then()
                .statusCode(404);
    }
}
