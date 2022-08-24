package com.cybertek.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter     // lombok
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)     // to ignore other unused fields: hasMore, limit, offset, links (jackson)
public class HwRegions {



    private List<Region> items;             // we call variables from Region class and name that list "items"
    private int count;




}
