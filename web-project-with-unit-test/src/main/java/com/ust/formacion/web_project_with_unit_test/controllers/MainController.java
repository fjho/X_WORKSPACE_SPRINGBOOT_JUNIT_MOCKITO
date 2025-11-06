package com.ust.formacion.web_project_with_unit_test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

        @GetMapping("/alive")
        public String isAlive() {
                return "UP";
        }
}
