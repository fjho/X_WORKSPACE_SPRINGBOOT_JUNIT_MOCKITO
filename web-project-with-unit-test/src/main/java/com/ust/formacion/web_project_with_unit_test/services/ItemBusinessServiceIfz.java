package com.ust.formacion.web_project_with_unit_test.services;

import java.util.List;

import com.ust.formacion.web_project_with_unit_test.model.Item;

public interface ItemBusinessServiceIfz {
    public Item retrieveHardcodedItem();
    public List<Item> retrieveAllItemFromDatabase();
    public Item retrieveItemFromDatabase(int id);
}
