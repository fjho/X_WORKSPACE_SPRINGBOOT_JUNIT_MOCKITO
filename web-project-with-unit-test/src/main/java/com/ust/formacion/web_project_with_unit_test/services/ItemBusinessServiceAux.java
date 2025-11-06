package com.ust.formacion.web_project_with_unit_test.services;

import org.springframework.stereotype.Service;

import com.ust.formacion.web_project_with_unit_test.model.Item;

@Service("itemBusinessServiceAux")
public class ItemBusinessServiceAux implements ItemBusinessServiceIfz {
    @Override
    public Item retrieveHardcodedItem() {
        return new Item(3, "ItemAux", 10, 10);
    }
}
