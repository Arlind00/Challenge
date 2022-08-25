package com.cybertek.Utilities;

import com.github.javafaker.Faker;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {


    public static Map<String, Object> nameCreator() {

        Faker faker = new Faker();
        Map<String, Object> data = new HashMap<>();

        String nameFaker = faker.letterify("????????");
        String gender = "";

        data.put("name", nameFaker);

        if (nameFaker.contains("z")) {
            data.put("gender", "Female");
        } else {
            data.put("gender", "Male");
        }

        data.put("phone", Integer.parseInt(faker.number().digits(10)));



        return data;
    }


}
