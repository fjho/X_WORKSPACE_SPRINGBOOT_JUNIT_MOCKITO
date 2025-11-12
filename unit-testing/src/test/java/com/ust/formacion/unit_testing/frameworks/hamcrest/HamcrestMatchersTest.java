package com.ust.formacion.unit_testing.frameworks.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HamcrestMatchersTest {

     @Test
     public void learningHamcrest() {
         List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

         assertThat(numbers, hasSize(10));
         assertThat(numbers, contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
         assertThat(numbers, hasItems(1, 2, 3, 4, 5));
         assertThat(numbers, everyItem(greaterThan(0)));
         assertThat(numbers, everyItem(lessThan(110)));

         assertThat("Hello World!", startsWith("Hello"));
         assertThat("Hello World!", endsWith("World!"));
         assertThat("", is(emptyString()));
         assertThat("ABCD", containsString("BC"));

         //https://hamcrest.org/JavaHamcrest/javadoc/3.0/org/hamcrest/Matchers.html
     }
}
