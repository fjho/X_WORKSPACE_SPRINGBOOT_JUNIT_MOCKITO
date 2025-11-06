package com.ust.formacion.web_project_with_unit_test.services;

import org.springframework.stereotype.Service;

import com.ust.formacion.web_project_with_unit_test.model.Item;

@Service("itemBusinessService")
public class ItemBusinessService implements ItemBusinessServiceIfz {
    @Override
    public Item retrieveHardcodedItem() {
        return new Item(2, "Item2", 10, 10);
    }
}
