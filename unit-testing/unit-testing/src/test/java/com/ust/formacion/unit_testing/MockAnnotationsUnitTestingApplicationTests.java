package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ust.formacion.unit_testing.business.BusinessService;
import com.ust.formacion.unit_testing.data.DataService;

//TESTING usando Injected MOCKS Junit5 + Mockito
@ExtendWith(MockitoExtension.class)
class MockAnnotationsUnitTestingApplicationTests {

	@Mock
	DataService dataServiceMock;

	@InjectMocks
	BusinessService business;

	@Test
	public void calculateSumUsingDataService_basic() {
		//mocking
		when(dataServiceMock.getData()).thenReturn(new int[] {1, 2, 3});

		assertEquals(6, business.calculateSumUsingDataService());	
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		//mocking
		when(dataServiceMock.getData()).thenReturn(new int[] {});

		assertEquals(0, business.calculateSumUsingDataService());	
	}
}