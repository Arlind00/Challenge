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
    public void test1() {


        Response response = get("/countries");          // shortest way to make a request


        // get the second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country ID
        List<String> allCountryID = jsonPath.getList("items.country_id");
        System.out.println("allCountryID = " + allCountryID);

        // get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region==2}.country_name");
        System.out.println("countryNameWithRegionId2 = " + countryNameWithRegionId2);
    }

    @DisplayName("GET request to Countries")
    @Test
    public void test2() {

        // we want to see all 107 employees in 1 body
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        // get all emails of employees wh are working as IT_PROG
        JsonPath jsonPath = response.jsonPath();

        List<String> employeeItProgrammer = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("employeeItProgrammer = " + employeeItProgrammer);


        // get first name of employees who is making more than 10000
        List<String> employeesWhoMakeMoreThan10Thousand = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeesWhoMakeMoreThan10Thousand = " + employeesWhoMakeMoreThan10Thousand);


    }


}
