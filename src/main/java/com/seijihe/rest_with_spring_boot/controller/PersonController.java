package com.seijihe.rest_with_spring_boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.seijihe.rest_with_spring_boot.model.Person;
import com.seijihe.rest_with_spring_boot.service.PersonService;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController 
@RequestMapping("/person")
public class PersonController {
    
    @Autowired 
    private PersonService service;

    @RequestMapping(value = "/{id}", 
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )    
    public Person findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @RequestMapping(
        method=RequestMethod.GET)
    public List<Person> findAll() {
        return service.findAll();
    }

    @RequestMapping(
        method=RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE, // Automatically for Spring (until yaml/xml), but manual for Sweeger
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @RequestMapping(
        method=RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) {
        return service.update(person);
    }

    @RequestMapping(value = "/{id}", 
        method = RequestMethod.DELETE
    )    
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
    
}
