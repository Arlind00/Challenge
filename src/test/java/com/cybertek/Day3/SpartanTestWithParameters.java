package com.cybertek.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods

import static io.restassured.RestAssured.baseURI;


public class SpartanTestWithParameters {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.45.47:8000";                        // base URL
    }



    /*   Given ACCEPT type is Json
         And ID parameter value is 5
         When user sends GET request to /api/spartans/{id}
         Then response status code should be 200
         And response content-type: application/json
         And "Blythe" should be in response payload
      */

    @DisplayName("GET request to api/spartans/{ID with ID 5")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)                                    // content type
                .and().pathParam("id", 5)        // path parameter
                .when()
                .get("/api/spartans/{id}");                           // end URL, we can give ID value directly also


        // verify response
        assertEquals(200, response.statusCode());

        // verify content-type
        assertEquals("application/json", response.contentType());

        // verify content
        assertEquals(response.body().asString().contains("Blythe"), true);         // we can use this
        assertTrue(response.body().asString().contains("Blythe"));                         // we can use this
    }




/*
        TASK
        Given accept type is Json
        And ID parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} with ID 500")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");


        // verify status code
        assertEquals(404, response.statusCode());

        // verify content- type
        assertTrue(response.contentType().contains("application/json"));

        // verify "Not Found" message on payload/body
        assertTrue(response.body().asString().contains("Not Found"));
    }



         /*
        Given ACCEPT type is Json
        And query parameter values are: gender|Female
                                        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3(){

        Response response = given().log().all()                                                             // optional to se our request info
                .accept(ContentType.JSON)
                .and().queryParam("gender", "Female")      // search for gender= female
                .and().queryParam("nameContains", "e")     // search for nameContains= e
                .when()
                .get("/api/spartans/search");


        // verify response
        assertEquals(200, response.statusCode());

        // verify content type
        assertEquals("application/json", response.contentType());

        // verify "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        // verify "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }









}
