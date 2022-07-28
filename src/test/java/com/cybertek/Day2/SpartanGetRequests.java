package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

public class SpartanGetRequests {


    String baseUrl = "http://52.91.45.47:8000";


/*
    Given Accept type application/json
    When user send GET request to api/spartans end point
    Then status code must be 200
    And response Content Type must be application/json
    And response body should include spartan result
 */

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)      // giving the Header type (we store everything in response variable)
                .when()                                                       //  optional to use
                .get(baseUrl + "/api/spartans");                           // base URL + endpoint


        // printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        // printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        // print whole result body
        response.prettyPrint();

        // verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        // verify response content
        Assertions.assertEquals(response.contentType(), "application/json");
    }







     /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan / api/spartans/3 and verify")
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)                   // giving the Header type (we store everything in response variable)
                .when()
                .get(baseUrl + "/api/spartans/3");


        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        // verify response content
        Assertions.assertEquals(response.contentType(), "application/json");        // compare actual result with expected result

        // verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));              // converts result at response into string and checks if it contains
                                                                                           // the result Fidole ( not a good (not specific enough) way to check)

    }
}
