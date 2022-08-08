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

import java.util.Map;

public class JSONtoJAVATest extends SpartanTestBase {


    @DisplayName("GET one Spartan and deserialize to Map")         // get specific spartan and convert the JSON to MAP
    @Test
    public void oneSpartanToMap(){


        Response response = given().pathParam("id", 15)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();


        // get the JSON and convert it to the MAP
        Map<String,Object> jsonMap = response.as(Map.class);            // as is the method that enables us to convert JSON to map (deserialization)

        System.out.println("jsonMap.toString() = " + jsonMap.toString());

        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, equalTo("Meta"));
    }




}
