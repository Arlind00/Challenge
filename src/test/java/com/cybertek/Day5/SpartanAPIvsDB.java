package com.cybertek.Day5;

import com.cybertek.Utilities.DBUtils;
import com.cybertek.Utilities.SpartanTestBase;


import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanAPIvsDB extends SpartanTestBase {


    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1() {


        //1. get id,name,gender phone  from database
        String query = "select  spartan_id, name, gender, phone from spartans\n" +
                "where spartan_id = 15";

        // save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);       //returns everything as string
        System.out.println(dbMap);


        //2. get same information from api
        Response respose = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartan/{id}")
                .then()
                .statusCode(404)
                .and().contentType("application/json")
                .extract().response();                      // cant fint the actual object, it returns 404

        // Deserilization Json to JAVA with Jackson objectMapper
        Map<String, Object> apiMap = respose.as(Map.class);
        System.out.println(apiMap);


        //3. compare database vs api, we assume expected result is db
        assertThat(apiMap.get("id"), is(dbMap.get("SPARTAN_ID")));
        assertThat(apiMap.get("name"), is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"), is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone"), is(dbMap.get("PHONE").toString()));


    }
}
