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
import com.seijihe.rest_with_spring_boot.dto.v1.PersonDTO;
import com.seijihe.rest_with_spring_boot.service.PersonServiceV1;

@RestController 
@RequestMapping("/api/v1/person")
public class PersonControllerV1 {
    
    // @Autowired 
    // private PersonService service;

    // @RequestMapping(value = "/{id}", 
    //     method = RequestMethod.GET,
    //     produces = MediaType.APPLICATION_JSON_VALUE
    // )    
    // public Person findById(@PathVariable("id") Long id) {
    //     return service.findById(id);
    // }

    private final PersonServiceV1 service;

    public PersonControllerV1(PersonServiceV1 service) {
        this.service = service;
    }

    /*
        If you take a look, @GetMapping is an alias for
        @RequestMapping(method = RequestMethod.GET)
    */

    @GetMapping(value = "/{id}", //
        produces = MediaType.APPLICATION_JSON_VALUE
    )    
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping()
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE, // Automatically for Spring (until yaml/xml), but manual for Sweeger
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO personDTO) {
        return service.create(personDTO);
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody PersonDTO personDTO) {
        return service.update(personDTO);
    }

    @DeleteMapping(value = "/{id}")    
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
    
}
