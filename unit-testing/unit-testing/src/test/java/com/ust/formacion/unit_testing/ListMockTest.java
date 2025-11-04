package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ListMockTest {
    @Test
    //test to return a specific value
    public void size_basic() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(5);
        // Your test code here
        assertEquals(5, mock.size());   
    }

    @Test
    //test to return different values on multiple calls
    public void returnDifferentValues() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(5).thenReturn(10);
        // Your test code here
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());  
    }

    @Test
    //test to return different values on multiple calls
    public void returnWithParameters() {
        List mock = mock(List.class);
        when(mock.get(Mockito.anyInt())).thenReturn("UST").thenReturn("Formacion");
        // Your test code here
        assertEquals("UST", mock.get(0));
        assertEquals("Formacion", mock.get(1));     
    }
}
