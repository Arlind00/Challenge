package com.cybertek.Day4;

import com.cybertek.Utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods


public class ORDSApiWithJsonPath extends HRTestBase {



    @DisplayName("GET request to Countries")
    @Test
    public void test1(){


        Response response = get("/countries");          // shortest way to make a request


        // get the second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country ID
        List<String> allCountryID = jsonPath.getList("items.country_id");
        System.out.println("allCountryID = " + allCountryID);


    }


}
