package com.cybertek.Day6;

import com.cybertek.POJO.Search;
import com.cybertek.POJO.Spartan;
import com.cybertek.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();


        //De serialize --> JSON to POJO (java custom class)

        // 1. using as() method
        Spartan spartan15 = response.as(Spartan.class);         // convert response to Spartan object (using Jackson for deserialize JSON to JAVA)
        System.out.println(spartan15);

        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println();


        // 2. using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();
        Spartan s15 = jsonPath.getObject("", Spartan.class);        // we don't need to write anything in path because there is only 1 person in response body
        // useful in very specific cases where body response is just one person
        System.out.println(s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());
    }


    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo() {

        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();


        //get the first spartan from content list and put inside spartan object

        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);   // we combine jsonPath and class (for cases names in class and db dont match)
        // more powerful method, it lets you filter body response through path
        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender() = " + s1.getGender());

    }


    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);            // we convert to search.class

        System.out.println(searchResult.getContent().get(0).getName());
    }


    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4() {

        List<Spartan> spartanList = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "a",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("items", Spartan.class);          // get the list from response body and put in Spartan.class

        System.out.println(spartanList.get(1).getName());

    }
}