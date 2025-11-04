package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ust.formacion.unit_testing.business.BusinessService;

//TESTING BASICO SIN MOCKS, solo usando JUnit5 (Jupiter)
class BasicUnitTestingApplicationTests {
	@Test
	public void calculateSum_basic() {
		BusinessService business = new BusinessService();
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertEquals(6, result);	
	}

	@Test
	public void calculateSum_empty() {
		BusinessService business = new BusinessService();
		int[] data = {};
		int result = business.calculateSum(data);
		assertEquals(0, result);	
	}
}