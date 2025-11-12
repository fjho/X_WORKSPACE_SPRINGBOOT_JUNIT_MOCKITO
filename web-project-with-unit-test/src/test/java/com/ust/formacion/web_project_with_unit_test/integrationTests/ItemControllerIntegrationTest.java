package com.ust.formacion.web_project_with_unit_test.integrationTests;

import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.repositories.ItemRepository;

// Integration Test
// Se levanta el contexto de Spring Boot y se prueba el endpoint real con la base de datos real en un puerto aleatorio
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

    
    @AfterAll
    static void cleanDb(@Autowired JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("DELETE FROM ITEM");
    }


    // Inyectamos el TestRestTemplate para hacer llamadas HTTP al endpoint
    // TestRestTemplate es una clase de Spring Boot que facilita las pruebas de servicios RESTful
    // El TestRestTemplate se configura automáticamente para apuntar al puerto aleatorio donde se está ejecutando la aplicación durante la prueba
    @Autowired
    private TestRestTemplate restTemplate;

    // Crea un mock de una clase o interfaz usando Mockito.
    // Registra ese mock como bean en el contexto de Spring, lo que permite que otros componentes (como controladores o servicios) lo reciban mediante @Autowired.
    // Permite usar Mockito.when(...) para definir el comportamiento del mock en tus pruebas.
    @MockitoBean
    ItemRepository itemRepositoryMock;


    @Test
	void contextLoads() {
	}

    //En las pruebas de integracion hay que simular las dependencias externas para que las pruebas sean rápidas y confiables
    //en este caso la BD es una dependencia externa por lo tanto el acceso a la BD lo mockeamos
    @Test
    public void testAllItemFromBusinessServiceBdIT() throws JSONException{
        when(itemRepositoryMock.findAll()).thenReturn(
            java.util.Arrays.asList(
                new Item(11, "Item1", 10, 100),
                new Item(22, "Item2", 20, 200),
                new Item(33, "Item3", 30, 300)
            )
        );
        String actual = restTemplate.getForObject("/all-items-from-business-service-bd", String.class);
        String expected = "[{id:11},{id:22},{id:33}]";
        JSONAssert.assertEquals(expected, actual, false);
    }
}
