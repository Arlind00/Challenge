package com.cybertek.POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id", allowSetters = true)         // serilization will not use it (do not use Id with request)
public class Spartan {                                           // deserilization will set it


        private int id;
        private String name;
        private String gender;
        private long phone;

}
