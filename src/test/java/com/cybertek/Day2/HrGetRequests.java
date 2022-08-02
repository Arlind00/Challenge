package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.RescaleOp;

public class HrGetRequests {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://52.91.45.47:1000/ords/hr";                        // base URL
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

    @DisplayName("Get request to /regions2")
    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .get("/regions2");                                  // endpoint

        // verify response code is 200
        System.out.println("response.statusCode() = " + response.statusCode());

        // verify content type is application/jason
        System.out.println("response.header(\"application/json\") = " + response.header("application/json"));

        // verify body contains =Americas



    }







}
