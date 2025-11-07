package com.ust.formacion.web_project_with_unit_test.controllers;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.services.ItemBusinessServiceIfz;

//TESTING usando Injected MOCKS Junit5 + Mockito
//Activa el soporte de Mockito en JUnit 5. Es obligatorio si usas anotaciones como @Mock y @InjectMocks.
@ExtendWith(MockitoExtension.class)
public class ItemControllerWithOutHttpRequestOnlyMokitoJunitTest {
    //Necesaro para hacer la prueba solo del controlador, sin lanzar peticion HTTP
        @Mock
        private ItemBusinessServiceIfz businessServiceOnlyTestControllerLogicMock;

        @InjectMocks
        private ItemController itemControllerOnlyTestControllerLogicMock;
    //FIN Necesaro para hacer la prueba solo del controlador, sin lanzar peticion HTTP

    //TESTS LOGICA CONTROLADOR SIN PETICION HTTP
        @Test
        public void testDummyItemNoWebRequest() throws Exception {
            Item item = itemControllerOnlyTestControllerLogicMock.dummyItem();

            String expectedJson = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
            String actuaString = item.toString();
            JSONAssert.assertEquals(expectedJson, actuaString, false);
        }

        @Test
        public void testItemFromBusinessServiceNoWebRequest() throws Exception {
            when(businessServiceOnlyTestControllerLogicMock.retrieveHardcodedItem()).thenReturn(new Item(1, "Ball", 10, 100));    
            Item item = itemControllerOnlyTestControllerLogicMock.itemFromBusinessService();

            String expectedJson = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
            String actuaString = item.toString();
            JSONAssert.assertEquals(expectedJson, actuaString, false);
        }
    //FIN TESTS LOGICA CONTROLADOR SIN PETICION HTTP
}
