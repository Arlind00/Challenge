package com.cybertek.Day4;

import com.cybertek.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods

import static io.restassured.RestAssured.baseURI;

public class ORDSApiTestWithPath extends HRTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.45.47:1000/ords/hr";
    }


    @DisplayName("GET request to countries with Path mehtod")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        // print hasMore
        System.out.println("response.path(\"hasmore\") = " + response.path("hasMore"));

        // print first country ID
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        // print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // print "href": "http://52.91.45.47:1000/ords/hr/countries/CA"
        String thirdCountryHref = response.path("items[2].links[0].href");
        System.out.println("secondCountryName = " + thirdCountryHref);

        // get all country names
        List<String> countryNames = response.path("items.country_name");        // gets all country names
        System.out.println("countryNames = " + countryNames);

        // assert all region ID's are equal to 2
        List<Integer> allRegionID = response.path("items.region_id");           // gets all region_id

        for (Integer regionID : allRegionID) {
            System.out.println("regionID = " + regionID);
            assertEquals(2, regionID);
        }
    }


    @DisplayName("GET request to employees with Query IT_PROG")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));      // checks for the value of specific header
        assertTrue(response.body().asString().contains("IT_PROG"));


        // make sure we have only IT Programmer as job_iD
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }
    }


    // print name of each IT-programmer

    @DisplayName("GET request to employees with Query IT_PROG")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));      // checks for the value of specific header
        assertTrue(response.body().asString().contains("IT_PROG"));


        // make sure we have only IT Programmer as job_iD
        List<String> namesOfProgrammers = response.path("items.first_name");

        for (String name : namesOfProgrammers) {
            System.out.println("name = " + name);
        }


    }




}
