package com.cybertek.Day6;

import com.cybertek.POJO.Employee;
import com.cybertek.POJO.HwRegions;
import com.cybertek.POJO.Region;
import com.cybertek.Utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {


    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).log().body().extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);          // we put specific part of body response into class
        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
    }


    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet() {

        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);        // we store only the first value in Employee.class
        // and in Employee class we specify which ones we want to use further
        System.out.println(employee1);
    }


    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore not used fields
     */


    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void regionHW() {

        HwRegions regions = get("/regions").then().statusCode(200)
                .extract().response().as(HwRegions.class);

        assertThat(regions.getCount(),is(4));

        // empty lists to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        List<Region> items = regions.getItems();

        for (Region region : items) {                   // we are filling regionNames and regionIds from item list
            regionIds.add(region.getRId());
            regionNames.add(region.getRegion_name());
        }
        System.out.println(regionIds);
        System.out.println(regionNames);


        //prepare expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(expectedRegionNames));

    }

}

