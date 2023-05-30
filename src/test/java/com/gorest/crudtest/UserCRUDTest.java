package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static int userId;

    @Test
    public void test001() { // Test to perform Post request
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("Unknown Shah");
        postsPojo.setEmail("shah" + TestUtils.getRandomValue() + "@gmail.com");
        postsPojo.setGender("Male");
        postsPojo.setStatus("Active");
        userId = given()
                .header("Authorization", "Bearer 7a89112fedbf68dad0159f5b495fcad87bab10e158c6ef9855bb0c2c62be11f1")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(postsPojo)
                .post("/users")
                .then().statusCode(201).extract().path("id");
    }

    @Test
    public void test002() { // Test to perform Put request
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Unknown Shah" + TestUtils.getRandomValue());
        userPojo.setEmail("shah" + TestUtils.getRandomValue() + "@gmail.com");
        userPojo.setStatus("Inactive");
        Response response = given()
                .header("Authorization", "Bearer 7a89112fedbf68dad0159f5b495fcad87bab10e158c6ef9855bb0c2c62be11f1")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/" + userId);
        response.then().statusCode(200);
    }

    @Test
    public void test003() { // Test to perform Delete request
        given()
                .header("Authorization", "Bearer 7a89112fedbf68dad0159f5b495fcad87bab10e158c6ef9855bb0c2c62be11f1")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("id", userId)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(204);

        given()
                .header("Authorization", "Bearer 7a89112fedbf68dad0159f5b495fcad87bab10e158c6ef9855bb0c2c62be11f1")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("id", userId)
                .when()
                .get("/users/{id}")
                .then().statusCode(404);
    }
    }

