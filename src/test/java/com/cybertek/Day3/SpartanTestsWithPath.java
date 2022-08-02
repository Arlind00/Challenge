package com.cybertek.Day3;


import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods


public class SpartanTestsWithPath {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.45.47:1000/ords/hr";
    }










    }
