package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ust.formacion.unit_testing.business.BusinessService;

//TESTING BASICO SIN MOCKS, solo usando JUnit5 (Jupiter) y stubs
class StubUnitTestingApplicationTests {
	@Test
	public void calculateSumUsingDataService_basic() {
		BusinessService business = new BusinessService(new DataServiceStub());
		int result = business.calculateSumUsingDataService();
		assertEquals(6, result);	
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		BusinessService business = new BusinessService(new DataServiceEmptyStub());
		int result = business.calculateSumUsingDataService();
		assertEquals(0, result);	
	}
}

class DataServiceStub implements com.ust.formacion.unit_testing.data.DataService {
	@Override
	public int[] getData() {
		return new int[] {1,2,3};
	}
}

class DataServiceEmptyStub implements com.ust.formacion.unit_testing.data.DataService {
	@Override
	public int[] getData() {
		return new int[] {};
	}
}