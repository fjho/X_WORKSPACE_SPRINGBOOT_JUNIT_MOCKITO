package com.ust.formacion.web_project_with_unit_test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.formacion.web_project_with_unit_test.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
}


