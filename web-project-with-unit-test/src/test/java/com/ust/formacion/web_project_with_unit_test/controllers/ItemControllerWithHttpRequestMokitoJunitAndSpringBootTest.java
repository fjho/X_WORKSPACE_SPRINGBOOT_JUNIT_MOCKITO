package com.ust.formacion.web_project_with_unit_test.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.services.ItemBusinessServiceIfz;

//@WebMvcTest carga solo la capa web (controladores), no los servicios reales.
//Si el controlador tiene una dependencia (ItemBusinessServiceIfz), Spring necesita un bean para inyectarlo.
//Este bean se genera con @TestConfiguration y @Bean dentro de la clase de prueba.
//Asi se hace en Junit5 con Spring Boot
@WebMvcTest(controllers = ItemController.class)
public class ItemControllerWithHttpRequestMokitoJunitAndSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    //En JUNIT4
        //@MockBean
        //private ItemBusinessServiceIfz businessServiceMock;
    //FIN En JUNIT4

    //En JUNIT5
        //Necesario para hacer la prueba realizando la peticion HTTP al controlador con WebMvcTest
        //@WebMvcTest solo carga la capa web (controladores), no los servicios reales. por eso ItemBusinessServiceIfz no está disponible
        //Sirve para registrar un bean mock en el contexto de Spring durante las pruebas.
        //@TestConfiguration crea una clase de configuración específica para las pruebas.
        //@Bean indica que el método produce un bean creado con mokito, Spring lo registra en el contexto
        //de la aplicación de prueba, por lo que cualquier componente que dependa de 
        //ItemBusinessServiceIfz (ItemController) recibirá este mock.
        @TestConfiguration
        static class TestConfig {
            @Bean
            ItemBusinessServiceIfz itemBusinessService() {
                return org.mockito.Mockito.mock(ItemBusinessServiceIfz.class);
            }
        }

        //Se inyecta el mock creado en TestConfig
        //para poder utilizar Mockito.when en los tests
        @Autowired
        private ItemBusinessServiceIfz businessServiceMock;
    //FIN En JUNIT5

    //TESTS LOGICA CONTROLADOR CON PETICION HTTP
        //In this test we only launch the web layer so we can test our controller
        @Test
        public void testDummyItemWithWebRequest() throws Exception {
            //create a get request that accepts JSON response
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
            
            //execute the request and return the result
            MvcResult mvcr = mockMvc.perform(requestBuilder)
                            .andExpect(status().isOk()) // we expect HTTP 200
                            //using json function to compare JSONs
                            //spaces are not important in this comparison
                            //the expected JSON can have more fields than the actual one, here we check that the expected fields are present with the expected values
                            .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                            .andExpect(content().json("{\"id\":    1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                            .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10}"))
                            .andReturn();

            System.out.println("Response: " + mvcr.getResponse().getContentAsString());

            JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10}", 
                                                    mvcr.getResponse().getContentAsString(), 
                                                    false);
            JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}", 
                                                    mvcr.getResponse().getContentAsString(), 
                                            false);
        }

        @Test  
        //En este test se realiza peticion HTTP al controlador  
        void testItemFromBusinessServiceWithWebRequest() throws Exception {        
            when(businessServiceMock.retrieveHardcodedItem()).thenReturn(new Item(1, "Ball", 10, 100));        
            mockMvc.perform(MockMvcRequestBuilders.get("/item-from-business-service")
                                                        .accept(MediaType.APPLICATION_JSON))
                                                        .andExpect(status().isOk())
                                                        .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"));
        }

        @Test  
        //En este test se realiza peticion HTTP al controlador  
        void testAllItemFromBusinessServiceWithWebRequest() throws Exception {  
            when(businessServiceMock.retrieveAllItemFromDatabase()).thenReturn(Arrays.asList(new Item(1, "Ball", 10, 100)));        
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-items-from-business-service-bd").accept(MediaType.APPLICATION_JSON);
            
            MvcResult mvcr = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
            System.out.println("Response: " + mvcr.getResponse().getContentAsString());

            String actualResponse = mvcr.getResponse().getContentAsString();
            //With JSONAssert scape special characters like " are not necessary
            String expectedResponse = "[{id:1,name:Ball,price:10,quantity:100}]";
            JSONAssert.assertEquals(expectedResponse, actualResponse,   false);
        }
    //FIN LOGICA CONTROLADOR CON PETICION HTTP
}
