package com.cybertek.Day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods

public class SpartanNegativeGetTest {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.45.47:8000";                        // base URL
    }


    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("Get request to /api/spartans/10")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                                    .when().get("/api/spartans/10");

        // verify status code
        assertEquals(406, response.statusCode());

        // verify status content
        assertEquals("application/xml;charset=UTF-8", response.contentType());
    }

}
