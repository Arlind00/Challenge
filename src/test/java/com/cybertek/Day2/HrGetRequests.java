package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.RescaleOp;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods

public class HrGetRequests {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {
         baseURI = "http://52.91.45.47:1000/ords/hr";                        // base URL
    }


    @DisplayName("Get request to /regions")
    @Test
    public void test1() {

        Response response = RestAssured.get("/regions");                          // endPoint
        System.out.println("response.statusCode() = " + response.statusCode());

    }




    /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains  =Americas
     */

    @DisplayName("Get request to /regions/2")
    @Test
    public void test2() {



        Response response = given().accept(ContentType.JSON)               // import static method
                .get("/regions/2");                                  // endpoint

        // verify response code is 200

        assertEquals(200, response.statusCode());

        // verify content type is application/jason
        assertEquals("application/json", response.contentType());

        // verify body contains Americas
        response.prettyPrint();

        assertEquals(response.body().asString().contains("Americas"), true);         // boolean


    }


}
