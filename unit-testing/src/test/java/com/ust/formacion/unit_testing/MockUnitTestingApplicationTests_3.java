package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.ust.formacion.unit_testing.business.BusinessService;
import com.ust.formacion.unit_testing.data.DataService;

//TESTING usando MOCKS
class MockUnitTestingApplicationTests_3 {

	DataService dataServiceMock = mock(DataService.class);

	@Test
	public void calculateSumUsingDataService_basic() {
		//mocking
		when(dataServiceMock.getData()).thenReturn(new int[] {1, 2, 3});
		BusinessService business = new BusinessService(dataServiceMock);
		assertEquals(6, business.calculateSumUsingDataService());	
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		//mocking
		when(dataServiceMock.getData()).thenReturn(new int[] {});

		BusinessService business = new BusinessService(dataServiceMock);
		assertEquals(0, business.calculateSumUsingDataService());	
	}

	@Test
	public void calculateSumFunctionalUsingDataService_basic() {
		//mocking
		when(dataServiceMock.getData()).thenReturn(new int[] {1, 2, 3});
		BusinessService business = new BusinessService(dataServiceMock);
		assertEquals(6, business.calculateSumUsingDataService());
	}
}