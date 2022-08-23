package com.cybertek.Utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {


    // is executed for entire class not just before each scenario as Hooks did, saves baseURL inside this variable
    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.173.186.7:8000";


        String dbUrl = "jdbc:oracle:thin:@54.173.186.7:1521:xe";    // connection string
        String dbUsername = "SP";                                   // connection string
        String dbPassword = "SP";                                   // connection string


        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }


    @AfterAll
    public static void teardown() {

        DBUtils.destroy();
    }


}
