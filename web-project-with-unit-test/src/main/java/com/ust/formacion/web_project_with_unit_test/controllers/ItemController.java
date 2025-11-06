package com.ust.formacion.web_project_with_unit_test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.services.ItemBusinessServiceIfz;

@RestController
public class ItemController {

        @Autowired
        @Qualifier("itemBusinessService")
        private ItemBusinessServiceIfz businessService;

        @GetMapping("/dummy-item")
        public Item dummyItem() {
                return new Item(1, "Ball", 10, 100);
        }

        @GetMapping("/item-from-business-service")
        public Item itemFromBusinessService() {
                return businessService.retrieveHardcodedItem();
        }

        @GetMapping("/all-items-from-business-service-bd")
        public List<Item> allItemsFromBusinessServiceBd() {
                return businessService.retrieveAllItemFromDatabase();
        }
}
