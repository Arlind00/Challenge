package com.cybertek.Day5;


import org.hamcrest.Matchers;                                                       // import statically
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;                                                  // import statically

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersIntro {


    @DisplayName("Assertion with numbers")
    @Test
    public void simpleTest() {

        // actual 5+5
        assertThat(5 + 5, Matchers.is(10));          // import Hamcrest(library) statically from RestAssured, compares 2 values

        assertThat(5 + 5, equalTo(10));            // compares 2 values
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));


        // number comparison
        // greaterThan()
        // greaterThanOrEqualTo()
        // lessThan()
        // lessThanOrEqualTo()

        assertThat(5 + 5, is(greaterThan(9)));
    }


    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {


        String text = "B25 us learning Hamcrest";

        assertThat(text, is("B25 us learning Hamcrest"));
        assertThat(text, equalTo("B25 us learning Hamcrest"));
        assertThat(text, is(equalTo("B25 us learning Hamcrest")));


        // check if this text stars with B25
        assertThat(text, startsWith("B25"));

        // case insensitive
        assertThat(text, startsWithIgnoringCase("b25"));
        assertThat(text, endsWithIgnoringCase("crest"));

        // check if text contains String learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LeArNiNg"));

        String str = " ";
        // check if string is blank
        assertThat(str, blankString());

        // checks if string we just trimmed is empty
        assertThat(str.trim(), emptyString());
    }


    @DisplayName("Assertion for Collection")
    @Test
    public void testCollection() {

        List<Integer> listOfNumbers = Arrays.asList(1, 4, 5, 6, 32, 54, 66, 77, 45, 23);

        // check size of the list
        assertThat(listOfNumbers, hasSize(10));

        // check for item in list
        assertThat(listOfNumbers,hasItem(77));
        assertThat(listOfNumbers,hasItems(77, 6));

        // check if all numbers are greater than 0
        assertThat(listOfNumbers, everyItem(greaterThan(0)));




    }


}
