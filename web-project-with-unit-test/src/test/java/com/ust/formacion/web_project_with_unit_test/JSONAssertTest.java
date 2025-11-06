package com.ust.formacion.web_project_with_unit_test;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JSONAssertTest {

        String actualResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
        
        @Test
        public void jsonassertionExample() throws JSONException {
                String expectedResponse1 = "{\"id\":     1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
                String expectedResponse2 = "{\"id\":     1,\"name\":\"Ball\"}";
                JSONAssert.assertEquals(expectedResponse1, actualResponse,   false);
                JSONAssert.assertEquals(expectedResponse2, actualResponse,   false);
                JSONAssert.assertEquals(expectedResponse1, actualResponse,   true);
                //this fails because in strict mode all fields must be present
                //white spaces are not important if they are out of the strings
                //JSONAssert.assertEquals(expectedResponse2, actualResponse,   true); 
        }
}
