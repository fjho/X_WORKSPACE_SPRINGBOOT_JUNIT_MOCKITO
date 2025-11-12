package com.ust.formacion.unit_testing.frameworks.assertJ;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AssertJTest {

     @Test
     public void learningHamcrest() {
         List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(numbers).hasSize(10)
                           .contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                           .contains(1, 2, 3, 4, 5)
                           .allMatch(n -> n > 0)
                           .allMatch(n -> n < 110)
                           .noneMatch(n -> n < 0);

         assertThat("Hello World!").startsWith("Hello")
                                   .endsWith("World!");
         assertThat("").isEmpty();
         assertThat("ABCD").contains("BC");

         //https://assertj.github.io/doc/

     }
}
