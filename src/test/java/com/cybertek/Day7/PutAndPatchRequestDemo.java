package com.cybertek.Day7;

import com.cybertek.Utilities.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest() {

        // just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Bruce Wayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8923456789L);


    }
}
