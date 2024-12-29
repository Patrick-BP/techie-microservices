package com.techie.microservices.product;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
 	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

 @LocalServerPort
 private int port;

 @BeforeEach
 void setUp() {
  RestAssured.baseURI = "http://localhost";
  RestAssured.port = port;
 }

 static {
  mongoDBContainer.start();

 }
	@Test
	void ShouldCreateProduct() {
	 String requestBody = """
			 {
			 "name": "Product 1",
			 "description": "Product 1 description",
			 "price": 100
			 }
			 """;
	 RestAssured.given()
			 	 .contentType("application/json")
			 	 .body(requestBody)
			 	 .when()
			 	 .post("/api/product")
			 	 .then()
			 	 .statusCode(201)
				 .body("id", (ResponseAwareMatcher<Response>) Matchers.notNullValue())
	 			 .body("name", (ResponseAwareMatcher<Response>) Matchers.equalTo("iPhone 15"))
			     .body("description", (ResponseAwareMatcher<Response>) Matchers.equalTo("iPhone 15 is a smartphone from Apple"))
			     .body("price", (ResponseAwareMatcher<Response>) Matchers.equalTo(100));

	}

}
