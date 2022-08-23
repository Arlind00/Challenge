package com.cybertek.POJO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter         // lombok annotation shortcut
@Setter         // lombok annotation shortcut
@ToString       // lombok annotation shortcut
public class Link {     // we created this class for ref & href keys inside links array

    private String rel;
    private String href;

}
