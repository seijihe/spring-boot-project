package com.seijihe.rest_with_spring_boot.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.seijihe.rest_with_spring_boot.dto.v2.PersonDTOV2;
import com.seijihe.rest_with_spring_boot.service.PersonServiceV2;

@RestController 
@RequestMapping("/api/v2/person")
public class PersonControllerV2{

    private final PersonServiceV2 service;

    public PersonControllerV2(PersonServiceV2 service) {
        this.service = service;
    }

    @GetMapping(
        value = "/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE
    )    
    public PersonDTOV2 findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping()
    public List<PersonDTOV2> findAll() {
        return service.findAll();
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE, // Automatically for Spring (until yaml/xml), but manual for Sweeger
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV2 create(@RequestBody PersonDTOV2 personDTO) {
        return service.create(personDTO);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV2 update(@RequestBody PersonDTOV2 personDTO) {
        return service.update(personDTO);
    }

    @DeleteMapping(value = "/{id}")    
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
    
}
