package com.ust.formacion.web_project_with_unit_test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.repositories.ItemRepository;
import com.ust.formacion.web_project_with_unit_test.services.ItemBusinessService;

//TESTING usando Injected MOCKS Junit5 + Mockito
//Activa el soporte de Mockito en JUnit 5. Es obligatorio si usas anotaciones como @Mock y @InjectMocks.
//En estos test no interviene SpringBoot por lo que no se usa @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemBusinessServiceMokitoJunitTest {

    //we cant use @Autowired here because its a unit test
    //SpringBoot context is not started
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Mock
    private ItemRepository itemRepositoryMock;

    @InjectMocks
    private ItemBusinessService itemBusinessServiceMock;

    @Test
    public void testRetrieveHardcodedItem() throws Exception {
        String expectedResponse = "{\"id\":2,\"name\":\"Item2\",\"price\":10,\"quantity\":10}";
        Item itm = itemBusinessServiceMock.retrieveHardcodedItem();
        JSONAssert.assertEquals(expectedResponse, objectMapper.writeValueAsString(itm),   false);
    }

    @Test
    //test if the logic of retrieveAllItemFromDatabase is correct
    //in this case the logic is calculate the total field (price * quantity)
    public void testRetrieveAllItemFromDatabase() throws Exception {
        when(itemRepositoryMock.findAll()).thenReturn(Arrays.asList(new Item(2, "Item2", 10, 10)));
        List<Item> litm = itemBusinessServiceMock.retrieveAllItemFromDatabase();
        assertEquals(100, litm.get(0).getTotal());
    }
}
