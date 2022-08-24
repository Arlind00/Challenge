package com.cybertek.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter             // lombok annotation shortcut
@Setter             // lombok annotation shortcut
@ToString           // lombok annotation shortcut

public class Region {

    @JsonProperty("region_id")      // cutting the connection between body respond key and variable name (json key and variable name dont match), from jackson
    private int rId;

    @JsonProperty("region_name")    // cutting the connection between body respond key and variable name (json key and variable name dont match), from jackson
    private String region_name;

    @JsonProperty("links")          // cutting the connection between body respond key and variable name (json key and variable name dont match), from jackson
    private List<Link> links;
}
