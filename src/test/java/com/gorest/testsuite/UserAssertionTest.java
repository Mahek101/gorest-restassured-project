package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20").
                then().statusCode(200);
    }

    //Verify the if the total record is 20
    @Test
    public void Test01() {
        response.body("id.size()", equalTo(20));

    }

    //Verify the if the name of id = 2272662,is equal to ”The Hon. Prema Guha”
    @Test
    public void Test02() {
        response.body("[2].name", equalTo("The Hon. Prema Guha"));
    }

    //Check the single ‘Name’ in the Array list (Abhisyanta Mukhopadhyay JD)
    @Test
    public void Test03() {
        response.body("[8].name", equalTo("Abhisyanta Mukhopadhyay JD"));
    }

    //Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. BodhanGuha, Karthik Dubashi IV)
    @Test
    public void Test04() {
        response
                .body("[10].name", equalTo("Sen. Rupinder Talwar"))
                .body("[5].name", equalTo("Dhyaneshwar Asan IV"))
                .body("[4].name", equalTo("Bhavani Abbott"));
    }

    //Verify the email of userid = 5471 is equal “deb_mehrotra@flatley.test"
    @Test
    public void Test05() {
        response.body("[15].email", equalTo("deb_mehrotra@flatley.test"));
    }

    //Verify the status is “Active” of user name is “Miss Ekaling Ganaka”
    @Test
    public void verifyTheStatus() {
        response.body("[2].status", equalTo("active"));
    }

    //Verify the Gender = male of user name is “Shiv Pothuvaal”
    @Test
    public void verifyTheGender() {
        //response.body("[0].findAll{it.name == 'Shiv Pothuvaal'}", hasItem(hasEntry("gender", "male")));
        response.body("[0].gender", equalTo("male"));

    }

}
