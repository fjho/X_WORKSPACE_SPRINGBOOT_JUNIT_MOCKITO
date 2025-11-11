package com.ust.formacion.web_project_with_unit_test.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.repositories.ItemRepository;

//Para que cargue la configuracion de la BD de pruebas del fichero de properties 
//src/test/resources/application-test.properties
//@ActiveProfiles("test")
//Alternativa a la anotacion @ActiveProfiles
//Se especifica directamente el fichero de properties a cargar
//@TestPropertySource(locations = "classpath:test-configuration.properties")
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private Environment environment;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    //Las consultas se hacen a la BD H" que se crea en memoria definida en src/test/resources/application-test.properties
    //En esta BD se cargan los datos definidos en test/resources/data-test.sql (4)
    public void testFindAll(){
        System.out.println("Perfil activo: " + Arrays.toString(environment.getActiveProfiles()));
        System.out.println("DB URL: " + environment.getProperty("spring.datasource.url"));
        List<Item>items = itemRepository.findAll();
        assertEquals(4, items.size());
    }

    @Test
    //Las consultas se hacen a la BD H" que se crea en memoria definida en src/test/resources/application-test.properties
    //En esta BD se cargan los datos definidos en test/resources/data-test.sql (4)
    public void testFindById(){
        System.out.println("Perfil activo: " + Arrays.toString(environment.getActiveProfiles()));
        System.out.println("DB URL: " + environment.getProperty("spring.datasource.url"));
        Optional<Item> item = itemRepository.findById(10001);
        assertTrue(item.isPresent());
    }
}
