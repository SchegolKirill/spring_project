package com.schegol.spring_project.controllers;


import com.schegol.spring_project.entities.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("hello")
    public Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting("Hello, " + name);
    }
}
