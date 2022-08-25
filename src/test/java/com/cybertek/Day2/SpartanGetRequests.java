package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.given;

public class SpartanGetRequests {


    String baseUrl = "http://54.173.186.7:8000";


    /*
    Given Accept type application/json
    When user send GET request to api/spartans end point
    Then status code must be 200
    And response Content Type must be application/json
    And response body should include spartan result
    */

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)                            // giving the Header type (we store everything in response variable)
                                    .when()                                             // optional to use
                                    .get(baseUrl + "/api/spartans");               // concatenating base URL + endpoint


        // printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        // printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        // print whole result body
        response.prettyPrint();

        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        // verify response content
        Assertions.assertEquals("application/json", response.contentType());
    }



    /*
     Given ACCEPT header is application/json
     When users send get request to /api/spartans/3
     Then status code should be 200
     And content type should be application/json
     and json body should contain Fidole
     */

    @DisplayName("GET one spartan / api/spartans/3 and verify")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)                   // giving the Header type (we store everything in response variable)
                .when()
                .get(baseUrl + "/api/spartans/3");


        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        // verify response content
        Assertions.assertEquals("application/json", response.contentType());        // compare actual result with expected result

        Assertions.assertTrue(response.body().asString().contains("Fidole"));              // converts result at response into string and checks if it contains
        // the result Fidole ( not a good (not specific enough) way to check)
    }



    /*
     Given no headers provided
     When Users send GET request to /api/hello
     Then response status code should be 200
     And Content type header should be “text/plain;charset=UTF-8”
     And header should contain date
     And Content-Length should be 17
     And body should be “Hello from Sparta"
     */

    @DisplayName("Get  request to /api/hello")
    @Test
    public void test3(){

        Response response = given().get(baseUrl + "/api/hello");

        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        // verify response content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType() );

        // verify we have headers named date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // we select one specific header from that we want to check
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));       // header returns only string
        System.out.println("response.header(\"Date\") =: " + response.header("Date"));


        // verify content length is equal 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        // verify body is “Hello from Sparta"
        Assertions.assertEquals( "Hello from Sparta", response.body().asString());
    }
}

