package com.ust.formacion.web_project_with_unit_test.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.repositories.ItemRepository;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    //La suma de todos los elementos es 7
    //los definidos en src/main/java/resources/data.sql (3)
    //los definidos en test/resources/data.sql (4)
    public void testFindAll(){
        List<Item>items = itemRepository.findAll();
        assertEquals(7, items.size());
    }

    @Test
    //El Item con id=10001 esta en el fichero test/resources/data.sql 
    public void testFindById(){
        Optional<Item> item = itemRepository.findById(10001);
        assertTrue(item.isPresent());
    }
}
