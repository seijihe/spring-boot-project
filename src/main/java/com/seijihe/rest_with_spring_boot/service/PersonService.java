package com.seijihe.rest_with_spring_boot.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seijihe.rest_with_spring_boot.dto.ObjectMapper;
import com.seijihe.rest_with_spring_boot.dto.PersonDTO;
import com.seijihe.rest_with_spring_boot.exception.ResourceNotFoundException;
import com.seijihe.rest_with_spring_boot.model.Person;
import com.seijihe.rest_with_spring_boot.repository.PersonRepository;


@Service 
public class PersonService {
    
    // private final AtomicLong counter = new AtomicLong();
    // @Autowired 
    // PersonRepository repository;

    private final PersonRepository repository;
    private final ObjectMapper mapper;

    public PersonService(PersonRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    /*
        Mock: create structures of code to be used temporarily, and in the future when it
        not used anymore, can be deleted. 
    */

    public List<PersonDTO> findAll() {

        logger.info("Finding all people");

        // List<Person> people = new ArrayList<Person>();

        // // Mock List Return
        // LongStream.rangeClosed(1, 5)
        //     .forEach(a -> {
        //         Person person = new Person();
        //         person.setId(a);
        //         person.setName("Person - " + a);
        //         person.setCompany("Company - " + a);
        //         person.setAge((int) a);
        //         people.add(person);
        //     });
        
 
        return mapper.personListToPersonDTOList(repository.findAll());
        
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding a Person at Database");

        // Person person = new Person();
        // person.setId(counter.incrementAndGet()); // Simulate persistency and return from Database
        // person.setName("Steve Jobs");
        // person.setCompany("Apple");
        // person.setAge(56);

        Person person = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));
        
        return mapper.personToPersonDTO(person);

    }

    public PersonDTO create(PersonDTO personDTO) {

        logger.info("Creating a person");
        
        Person person = mapper.personDTOToPerson(personDTO);

        Person saved = repository.save(person);

        return mapper.personToPersonDTO(saved);

    }

    public PersonDTO update(PersonDTO personDTO) {

        logger.info("Updating a person");

        Person person = mapper.personDTOToPerson(personDTO);

        Person entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));

        entity.setName(person.getName());
        entity.setAge(person.getAge());
        entity.setCompany(person.getCompany());
        
        return mapper.personToPersonDTO(entity);
        
    }

    public void delete(Long id) {

        logger.info("Deleting a person");

        Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));

        repository.delete(entity);

    }
}
