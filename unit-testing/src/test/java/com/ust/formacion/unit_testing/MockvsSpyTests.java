package com.ust.formacion.unit_testing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;


public class MockvsSpyTests {
    @Test
    public void mockTest(TestInfo testInfo) {
        
        @SuppressWarnings("unchecked")
        ArrayList<String> arrayListMock = (ArrayList<String>) mock(ArrayList.class);

        System.out.println(testInfo.getDisplayName()+": "+arrayListMock.get(0));//returns null because it is a mock object
        arrayListMock.add("MOCK01");
        System.out.println(testInfo.getDisplayName()+": "+arrayListMock.size());//returns null because it is a mock object, the previous add has no effect
        arrayListMock.add("MOCK02");
        arrayListMock.add("MOCK03");
        System.out.println(testInfo.getDisplayName()+": "+arrayListMock.size());//returns null because it is a mock object, the previous add has no effect
        when(arrayListMock.size()).thenReturn(5);
        System.out.println(testInfo.getDisplayName()+": "+arrayListMock.size());//returns 5 because we have stubbed this method
    }

    @Test
    public void spyTest(TestInfo testInfo) {
        @SuppressWarnings("unchecked")
        ArrayList<String> arrayListSpy = (ArrayList<String>) spy(ArrayList.class);

        //System.out.println(arrayListSpy.get(0));//returns an exception because it is the real behavior fo the ArrayList object

        arrayListSpy.add("SPY01");
        System.out.println(testInfo.getDisplayName()+": "+arrayListSpy.size());//returns 1 because spy is the real object is the behavior fo the ArrayList object
        when(arrayListSpy.size()).thenReturn(5);
        System.out.println(testInfo.getDisplayName()+": "+arrayListSpy.size());//if we set a value for size method, it returns that value
        arrayListSpy.add("SPY02");
        System.out.println(testInfo.getDisplayName()+": "+arrayListSpy.size());//it has no effect add calls, it returns the stubbed value
    }
}


