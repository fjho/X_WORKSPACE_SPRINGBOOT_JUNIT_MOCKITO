package com.ust.formacion.unit_testing;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.ust.formacion.unit_testing.business.BusinessService;

//TESTING BASICO SIN MOCKS, solo usando JUnit5 (Jupiter)
class BasicUnitTestingApplicationTests {
	static BusinessService business;

	@BeforeAll
	//este metodo tiene que ser static
	static void setupAll() {
		System.out.println("Inicializando tests...");
		business = new BusinessService();
	}

	@AfterAll
	//este metodo tiene que ser static
	static void finishAll() {
		System.out.println("Finalizando tests...");
		business = null;
	}

	@BeforeEach
	void setupEach(TestInfo testInfo) {
		System.out.println("Ejecutando test: " + testInfo.getDisplayName());
		business = new BusinessService();
	}

	@AfterEach
	void finishEach(TestInfo testInfo) {
		System.out.println("Finalizado test: " + testInfo.getDisplayName());
		business = new BusinessService();
	}

	@Test
	public void calculateSum_basic_equals() {
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertEquals(6, result, "Valor esperado es 6");	
	}

	@Test
	public void calculateSum_basic_notEquals() {
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertNotEquals(7, result, "Valor esperado no es 7");	
	}

	@Test
	public void calculateSum_basic_true() {
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertTrue(result==6, "Valor esperado es 6");	
	}

	@Test
	public void calculateSum_basic_false() {
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertFalse(result==7, "Valor esperado es distinto de 7");	
	}

	@Test
	public void calculateSum_basic_null() {
		assertNull(null, "Valor esperado es null");	
	}

	@Test
	public void calculateSum_basic_notNull() {
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertNotNull(result, "Valor esperado es no null");	
	}

	@Test
	public void calculateSum_basic_empty() {
		int[] data = {};
		int result = business.calculateSum(data);
		assertEquals(0, result, "Valor esperado es 0");	
	}

	@Test
	public void calculateSum_basic_all() {
		int[] data = {1, 2, 3};
		int result = business.calculateSum(data);
		assertAll(
			() -> assertEquals(6,result),
			() -> assertNotEquals(7, result),
			() ->  assertFalse(result==7),
			() -> assertTrue(result==6),
			() -> assertNotNull(result),
			() -> assertNull(null),
			() -> assertTrue("JUnit".startsWith("J")),
			() -> assertNotNull("Hello")
		);
	}

	@Test
	public void calculateSum_basic_exception_functional() {
		assertThrows(IllegalArgumentException.class, () -> {
 			throw new IllegalArgumentException("Error");
		});
	}


    @Test
    public void calculateSum_basic_exception() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                throw new IllegalArgumentException("Error");
            }
        });
    }

	@Test
	public void calculateSum_basic_timeout_functional() {
		assertTimeout(Duration.ofMillis(100), () -> {
 			Thread.sleep(50);
		});
	}

	@Test
	public void calculateSum_basic_timeout() {
		assertTimeout(Duration.ofMillis(100), new Executable() {
            @Override
            public void execute() throws Throwable {
                Thread.sleep(50);
            }
        });
	}

	@RepeatedTest(5)
	public void calculateSum_basic_arrays() {
		assertArrayEquals(new int[]{1, 2}, new int[]{1, 2});
	}


    @ParameterizedTest
    @ValueSource(strings = {"JUnit", "Test", "Java"})
    void stringsShouldNotBeEmpty(String word) {
        System.out.println("Probando con: " + word);
        assertTrue(word.length() > 0);
    }

	
    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "10, 5, 15",
        "7, 8, 15"
    })
    void testSum(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }



	@Disabled
	@Test
	public void calculateSum_disabled() {
		int[] data = {};
		int result = business.calculateSum(data);
		assertEquals(0, result);	
	}
}