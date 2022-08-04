package com.cybertek.Day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods

import static io.restassured.RestAssured.baseURI;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        baseURI = "http://api.cybertektraining.com";
    }






    @DisplayName("Get request to individual Student")
    @Test
    public void test1(){


        // send a get request to student id 32801 as a path parameter and accept header application/json
        // verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        // verify Date header exists
        // assert that

            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */

        Response response = given().accept(ContentType.JSON)
                .when().pathParam("id", 32801)
                .get("/student/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip", response.header("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));



        JsonPath jsonPath = response.jsonPath();


        String firstName = jsonPath.getString("students[0].firstName");
        System.out.println("firstName = " + firstName);
        assertEquals("Vera", jsonPath.getString("students[0].firstName"));


        int batchNo = jsonPath.getInt("students[0].batch");
        System.out.println("batchNo = " + batchNo);
        assertEquals(14, jsonPath.getInt("students[0].batch"));


        int sectionNO = jsonPath.getInt("students[0].section");
        System.out.println("sectionNO = " + sectionNO);
        assertEquals(12, jsonPath.getInt("students[0].section"));



        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println("emailAddress = " + emailAddress);
        assertEquals("aaa@gmail.com", jsonPath.getString("students[0].contact.emailAddress"));


        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println("companyName = " + companyName);
        assertEquals("Cybertek", jsonPath.getString("students[0].company.companyName"));

        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println("state = " + state);
        assertEquals("IL", jsonPath.getString("students[0].company.address.state"));

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(60606, jsonPath.getInt("students[0].company.address.zipCode"));
    }


}
