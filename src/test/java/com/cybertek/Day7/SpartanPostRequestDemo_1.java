package com.cybertek.Day7;

import com.cybertek.POJO.Spartan;
import com.cybertek.Utilities.SpartanTestBase;
import com.cybertek.Utilities.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarEntry;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo_1 extends SpartanTestBase {


    /*
    Given ACCEPT type and Content type is JSON
    And request json body is:
        {
          "gender":"Male",
          "name":"Severus",
          "phone":8877445596
       }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
        "A Spartan is Born!" message
    and same data what is posted
    */


    @Test
    public void postMethod1() {

        String requestJsonBody = "{\n" + "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Mike\",\n" +
                "  \"phone\": 9845315689\n" + "}";


        Response response = given().accept(ContentType.JSON).and()          // what we are asking from api which is JSON response
                .contentType(ContentType.JSON)                              // what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");                               // till today, we used GET, first time we are using POST


        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";                            // expected response message

        // Hemcrest assertions
        assertThat(response.path("success"), is(expectedResponseMessage));          // we use path method to retrieve information
        assertThat(response.path("data.name"), is("Mike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9845315689L));
    }


    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2() {

        // create a map to keep request json body information
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();              // object because value could be anything, linkedHashMap for order
        requestJsonMap.put("name", "Mike");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 9845315689L);


        Response response = given().accept(ContentType.JSON).and()          // what we are asking from api which is JSON response
                .contentType(ContentType.JSON)                              // what we are sending to api, which is JSON also
                .body(requestJsonMap)                                       // serilization done implicitly for PATCH, PUT and POST
                .log().all()
                .when()
                .post("/api/spartans");                               // till today, we used GET, first time we are using POST


        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";                            // expected response message

        // Hemcrest assertions
        assertThat(response.path("success"), is(expectedResponseMessage));          // we use path method to retrieve information
        assertThat(response.path("data.name"), is("Mike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9845315689L));

        response.prettyPrint();
    }


    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3() {


        Spartan spartan = new Spartan();                // create a new spartan object
        spartan.setName("Mike");
        spartan.setGender("Male");
        spartan.setPhone(9845315689L);


        Response response = given().accept(ContentType.JSON).and()          // what we are asking from api which is JSON response
                .contentType(ContentType.JSON)                              // what we are sending to api, which is JSON also
                .body(spartan)                                              // pass down object in body
                .log().all()
                .when()
                .post("/api/spartans");                               // till today, we used GET, first time we are using POST


        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";                            // expected response message

        // Hemcrest assertions
        assertThat(response.path("success"), is(expectedResponseMessage));          // we use path method to retrieve information
        assertThat(response.path("data.name"), is("Mike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9845315689L));

        response.prettyPrint();


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4() {

        // At this example we implement serialization, creating spartan object sending as a request body
        // also implemented deserialization getting the id, sending get request and saving that body as a response

        Spartan spartan = new Spartan();                                // create a new spartan object
        spartan.setName("Mike");
        spartan.setGender("Male");
        spartan.setPhone(9845315689L);

        String expectedResponseMessage = "A Spartan is Born!";           // expected response message

        int idFromPost = given().accept(ContentType.JSON).and()          // what we are asking from api which is JSON response
                .contentType(ContentType.JSON)                           // what we are sending to api, which is JSON also
                .body(spartan)                                           // pass down object in body (implicitly Java will be converted to Json)
                .log().all()
                .when()
                .post("/api/spartans")                             // till today, we used GET, first time we are using POST
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");            // this line determines the type of variable that it will be stored in

        // send a get request to id ( we want to get the spartan that we created
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)          // we are giving dynamic path parameter
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().all().extract().as(Spartan.class);   // we are putting everything from response to Spartan.class (deserilization)


        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));

    }


    //  HOMEWORK
    // Create one SpartanUtil class
    // create a static method that returns Map<String,Object>
    // use faker library(add as a dependency) to assign each time different information
    // for name,gender,phone number
    // then, use your method for creating spartan as a map,dynamically.


    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod5() {


        Map<String, Object> spartanHomework = SpartanUtil.nameCreator();                // create a new spartan object
        // String name1 = (String) spartanHomework.get("name");
        // String gender1 = (String) spartanHomework.get("gender");
        // String phone1 = (String) spartanHomework.get("phone");

        System.out.println(spartanHomework);


        Response response = given().accept(ContentType.JSON)                // what we are asking from api which is JSON response
                .and()
                .contentType(ContentType.JSON)                              // what we are sending to api, which is JSON also
                .body(spartanHomework)                                      // pass down object in body
                .log().all()
                .when()
                .post("/api/spartans");                               // till today, we used GET, first time we are using POST


        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";                            // expected response message

        // Hemcrest assertions
        //     assertThat(response.path("success"), is(expectedResponseMessage));          // we use path method to retrieve information
        //     assertThat(response.path("data.name"), is(name1));
        //     assertThat(response.path("data.gender"), is(gender1));
        //     assertThat(response.path("data.phone"), is(phone1));

        response.prettyPrint();

    }
}

