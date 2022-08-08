package com.cybertek.Day5;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static groovy.json.JsonOutput.prettyPrint;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersApiTest {



    /*
       given ACCEPT type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {

        given().
                accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("http://54.173.186.7:8000/api/spartans/{id}")                       // till here is request
                .then()                                                                      // from here starts response
                .statusCode(200)
                .and()
                .contentType("application/json")
                .body("id", equalTo(15),                                       // assertion begins (Validation / verify)
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106));
    }


    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData() {


        given().accept(ContentType.JSON)
                .and()
                .log().all()
                //.pathParam("id", 14104)
                .when()
                .get("http://api.cybertektraining.com/teacher/14104")
                .then()
                .statusCode(200)                                                            // response part
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", "280")
                .and()
                .header("Date", notNullValue())                         // checks if the date is not null will be OK
                .and().assertThat();
                //.body("teacher[0].firstName", is("Verda"))
                //.body("teacher[0].lastName", is("Parker"))
                //.body("teacher[0].gender", equalTo("male"));
    }


}







