package com.seijihe.rest_with_spring_boot.controller;

import org.springframework.web.bind.annotation.RestController;
import com.seijihe.rest_with_spring_boot.model.Greeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class GreetingControllers {
    
    private static final String template = "Hello, %s! A good meal is %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting") //http://localhost:8080/greeting
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World") //http://localhost:8080/greeting?name=Seiji
            String name,
            @RequestParam(value = "food", defaultValue = "Pizza")
            String food) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name, food));
    }

}
