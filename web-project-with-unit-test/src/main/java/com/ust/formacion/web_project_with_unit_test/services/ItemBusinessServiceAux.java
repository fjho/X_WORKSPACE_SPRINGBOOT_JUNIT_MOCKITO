package com.ust.formacion.web_project_with_unit_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.formacion.web_project_with_unit_test.model.Item;
import com.ust.formacion.web_project_with_unit_test.repositories.ItemRepository;

@Service("itemBusinessServiceAux")
public class ItemBusinessServiceAux implements ItemBusinessServiceIfz {

    @Autowired
    private ItemRepository itemRepository;   

    @Override
    public Item retrieveHardcodedItem() {
        return new Item(3, "ItemAux", 10, 10);
    }

    @Override
    public Item retrieveItemFromDatabase(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> retrieveAllItemFromDatabase() {
        return itemRepository.findAll();
    }
}
