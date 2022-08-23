package com.cybertek.Day6;

import com.cybertek.POJO.Employee;
import com.cybertek.POJO.Region;
import com.cybertek.Utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


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
        public void employeeGet () {

            Employee employee1 = get("/employees").then().statusCode(200)
                    .extract().jsonPath().getObject("items[0]", Employee.class);        // we store only the first value in Employee.class
                                                                                             // and in Employee class we specify which ones we want to use further
            System.out.println(employee1);


        }

    }
