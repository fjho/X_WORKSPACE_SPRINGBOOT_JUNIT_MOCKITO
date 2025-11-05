package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

//OPCION2: using @Mock annotation and initializing the mock with MockitoExtension in the test class
//@ExtendWith(MockitoExtension.class)
public class ListMockTest {

    //OPCION1: manual mock creation
    //private List<String> mock = mock(List.class);

    //OPCION2: using @Mock annotation and initializing the mock with MockitoExtension in the test class
    //@Mock
    //private List<String> mock;

    //OPCION3: using @Mock annotation and initializing the mock in before all method with MockitoAnnotations.openMocks(this);
    @Mock
    private List<String> mock;  

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    //test to return a specific value
    public void size_basic() {
        when(mock.size()).thenReturn(5);
        // Your test code here
        assertEquals(5, mock.size());   
    }

    @Test
    //test to return different values on multiple calls with specific parameters
    public void returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        // Your test code here
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());  
    }

    @Test
    //test to return different values on multiple calls with generic int parameter
    //anyInt() is a matcher that matches any integer value
    public void returnWithParameters() {
        when(mock.get(anyInt())).thenReturn("UST");
        // Your test code here
        assertEquals("UST", mock.get(0));
        assertEquals("UST", mock.get(1));     
    }

    @Test
    //Verify that the methods of a mock are being called the expected number of times
    public void verificationsBasics() {
        //SUT (system Under Test)(Codigo que se esta probando)
        String value1 = mock.get(0);
        String value2 = mock.get(1);
        String value3 = mock.get(1);

        //Verifications about the calls made to the get method of the mock list
        //verify that get(0) was called once
        verify(mock).get(0);
        //verify that get(1) was called twice
        verify(mock, times(2)).get(1);
        //verify that get(2) was never called
        verify(mock, never()).get(2);
        //verify that get(1) was called at least once
        verify(mock, atLeast(1)).get(1);
        //verify that get(1) was called at least once
        verify(mock, atLeastOnce()).get(1);
        //verify that get(1) was called at most two times
        verify(mock, atMost(2)).get(1);
    }

    @Test
    //Verify that the methods of a mock are being called the expected number of times
    public void verificationsArgumentCapture() {
        //SUT (system Under Test)(Codigo que se esta probando)
        mock.add("HELLO");

        //Verifications get the arguments passed to the add method of the mock list with an ArgumentCaptor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());
        System.out.println(captor.getValue());
        assertEquals("HELLO", captor.getValue());
    }


    @Test
    //Verify that the methods of a mock are being called the expected number of times
    public void verificationsMultipleArgumentCapture() {
        //SUT (system Under Test)(Codigo que se esta probando)
        mock.add("HELLO1");
        mock.add("HELLO2");



        //Verifications get the arguments passed to the add method of the mock list with an ArgumentCaptor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock,atLeastOnce()).add(captor.capture());

        assertEquals("HELLO1", captor.getAllValues().get(0));
        assertEquals("HELLO2", captor.getAllValues().get(1));
    }

    @Test
    //Verify that the methods of a mock are being called the expected number of times
    public void verificationsInOrder() {
        //SUT (system Under Test)(Codigo que se esta probando)
        mock.add("Primero");
        mock.add("Segundo");
        mock.clear();


        // Crear verificador de orden
        InOrder inOrder = inOrder(mock);

        // Verificar que las llamadas ocurrieron en este orden
        inOrder.verify(mock).add("Primero");
        inOrder.verify(mock).add("Segundo");
        inOrder.verify(mock).clear();
    }
}
