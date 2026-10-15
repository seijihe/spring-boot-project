package com.seijihe.rest_with_spring_boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.seijihe.rest_with_spring_boot.model.Person;
import com.seijihe.rest_with_spring_boot.service.PersonService;

@RestController 
@RequestMapping("/person")
public class PersonController {
    
    @Autowired 
    private PersonService service;

    // @RequestMapping(value = "/{id}", 
    //     method = RequestMethod.GET,
    //     produces = MediaType.APPLICATION_JSON_VALUE
    // )    
    // public Person findById(@PathVariable("id") Long id) {
    //     return service.findById(id);
    // }

    /*
        If you take a look, @GetMapping is an alias for
        @RequestMapping(method = RequestMethod.GET)
    */

    @GetMapping(value = "/{id}", //
        produces = MediaType.APPLICATION_JSON_VALUE
    )    
    public Person findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping()
    public List<Person> findAll() {
        return service.findAll();
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE, // Automatically for Spring (until yaml/xml), but manual for Sweeger
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")    
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
    
}
