package com.cybertek.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods


public class ORDSApiTestsWithParameters {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.45.47:1000/ords/hr";                        // base URL
    }



    /*
        Given accept type is Json
        And parameters: q = {\"region_id\":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param ")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));      // checks for the value of specific header
        assertTrue(response.headers().hasHeaderWithName("\"Content-Type\""));       // checks if the specific header exists
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();
    }



     /*
        Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @DisplayName("GET request to employees with Query IT_PROG")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .log().all()
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));      // checks for the value of specific header
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();
    }







}
