package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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

    //Extract the All Ids
    @Test
    public void extractAllIds() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the all Names
    @Test
    public void extractAllNames(){
        List<String> names = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the name of 5th object
    @Test
    public void extractTheNameOf5thObject(){
        String name = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the names of all object whose status = inactive
    @Test
    public void extractTheNames(){
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of all object whose status = inactive are: " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the gender of all the object whose status = active
    @Test
    public void extractTheGender(){
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active are: " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
    //Print the names of the object whose gender = female
    @Test
    public void printTheNames(){
        List<String> pNames = response.extract().path("findAll{it.gender = 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female are: " + pNames);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get all the emails of the object where status = inactive
    @Test
    public void getAllTheEmails(){
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the emails of all object whose status = inactive are: " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get the ids of the object where gender = male
    @Test
    public void getTheIds(){
        List<Integer> ids = response.extract().path("findAll{it.gender = 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object whose gender = male are: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get all the status
    @Test
    public void getAllTheStatus(){
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the status are: " + status);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get email of the object where name = Shiv Pothuvaal
    @Test
    public void getEmail(){
        String email = response.extract().path("findAll{it.name == 'Shiv Pothuvaal'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email of the object where name = Shiv Pothuvaal : " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get gender of id = 2272680
    @Test
    public void getGender(){
        String gender = response.extract().path("[1].gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender of id = 2272680 is : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

}
