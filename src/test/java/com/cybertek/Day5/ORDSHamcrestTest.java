package com.cybertek.Day5;


import com.cybertek.Utilities.HRTestBase;
import com.github.fge.jsonschema.core.tree.SchemaTree;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static groovy.json.JsonOutput.prettyPrint;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ORDSHamcrestTest extends HRTestBase {


    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void regionTest() {

        // send a get request to empoloyees endpoint with query parameter job_id IT_PROG
        // verify each job_id is IT_PROG
        // verify first names are (find proper method to check list against list)   Alexander, Bruce, David, Valli, Diana
        // verify emails without checking order (provide email in different order, just make sure it has same emails)


        List<String> names =  Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))            // from everyItem in body selects only IT_PROG's
                //.body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));              // checks body according the order we write the list
                .body("items.first_name", containsInAnyOrder("Alexander", "Bruce", "David", "Valli", "Diana"))    // checks body without order
                .body("items.first_name", equalTo(names));           // equality of lists assertion (actual and expected)
    }



    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest2() {

        // we want chain alto to get response object


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .log().all()
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .log().all()
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();          // we add this method in order to be able to store query and response to response,jsonpath Object, int, String ect
                                                // extract() allow us to get respond objects after we use then() method



        // assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));        // checks the list if it has 5 items


        // assert firstnames order
        // assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));    // checks order of the list

    }





}
