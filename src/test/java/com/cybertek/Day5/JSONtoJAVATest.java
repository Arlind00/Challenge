package com.cybertek.Day5;

import static groovy.json.JsonOutput.prettyPrint;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cybertek.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class JSONtoJAVATest extends SpartanTestBase {


    @DisplayName("GET one Spartan and deserialize to Map")         // get specific spartan and convert the JSON to MAP
    @Test
    public void oneSpartanToMap() {


        Response response = given().pathParam("id", 15)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();


        // get the JSON and convert it to the MAP
        Map<String, Object> jsonMap = response.as(Map.class);            // as is the method that enables us to convert JSON to map (deserialization)

        System.out.println("jsonMap.toString() = " + jsonMap.toString());

        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, equalTo("Meta"));
    }


    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan() {


        Response response = given().accept(ContentType.JSON)
                .and()
                .get("/api/spartans")
                .then()
                .statusCode(200).extract().response();

        // we need to convert from JSON to Java
        List<Map<String, Object>> jsonList = response.as(List.class);            // multiple maps are stored as List of Maps

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));       // get(1) selects 2nd map, get(name) selects name map inside 2nd map


        Map<String, Object> spartan3 = jsonList.get(2);
        System.out.println("spartan3 = " + spartan3);
    }




}





