package com.cybertek.Day7;

import com.cybertek.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo_1 extends SpartanTestBase {


    /*
    Given ACCEPT type and Content type is JSON
    And request json body is:
        {
          "gender":"Male",
          "name":"Severus",
          "phone":8877445596
       }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
        "A Spartan is Born!" message
    and same data what is posted
    */



    @Test
    public void postMethod1(){

        String requestJsonBody = "{\n" +  "  \"gender\": \"Male\",\n" +
                                 "  \"name\": \"Mike\",\n" +
                                 "  \"phone\": 9845315689\n" + "}";



        Response response = given().accept(ContentType.JSON).and()          // what we are asking from api which is JSON response
                .contentType(ContentType.JSON)                              // what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");                               // till today, we used GET, first time we are using POST


        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";                            // expected response message

        // Hemcrest assertions
        assertThat(response.path("success"), is(expectedResponseMessage));          // we use path method to retrieve information
        assertThat(response.path("data.name"), is("Mike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9845315689L));
    }





    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2(){

        // create a map to keep request json body information
        Map<String,Object> requestJsonMap = new LinkedHashMap<>();              // object because value could be anything, linkedHashMap for order
        requestJsonMap.put("name","Mike");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone",9845315689L);


        Response response = given().accept(ContentType.JSON).and()          // what we are asking from api which is JSON response
                .contentType(ContentType.JSON)                              // what we are sending to api, which is JSON also
                .body(requestJsonMap)                                       // serilization done implicitly for PATCH, PUT and POST
                .log().all()
                .when()
                .post("/api/spartans");                               // till today, we used GET, first time we are using POST


        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";                            // expected response message

        // Hemcrest assertions
        assertThat(response.path("success"), is(expectedResponseMessage));          // we use path method to retrieve information
        assertThat(response.path("data.name"), is("Mike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9845315689L));

        response.prettyPrint();
    }
}

