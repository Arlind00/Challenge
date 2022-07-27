package com.cybertek.Day1;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {


    String url = "http://52.91.45.47:8000/api/spartans";

    @Test
    public void test1() {

        // send a get request and save response inside the response object
        Response response = RestAssured.get(url);                       // restassured.get then alt and enter
        System.out.println(response.statusCode());

        response.prettyPrint();

    }


}
