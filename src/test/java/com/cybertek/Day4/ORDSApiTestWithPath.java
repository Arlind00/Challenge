package com.cybertek.Day4;

import com.cybertek.Utilities.HRTestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;                  // to be able to import static methods
import static org.junit.jupiter.api.Assertions.*;            // to be able to import static methods

import static io.restassured.RestAssured.baseURI;

public class ORDSApiTestWithPath extends HRTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.45.47:1000/ords/hr";
    }


    @DisplayName("GET request to countries with Path mehtod")
    @Test
    public void test1(){



    }


}
