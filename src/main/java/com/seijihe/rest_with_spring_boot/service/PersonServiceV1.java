package com.seijihe.rest_with_spring_boot.service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.seijihe.rest_with_spring_boot.controller.PersonControllerV1;
import com.seijihe.rest_with_spring_boot.dto.v1.PersonDTO;
import com.seijihe.rest_with_spring_boot.dto.v1.PersonDTOMapper;
import com.seijihe.rest_with_spring_boot.exception.ResourceNotFoundException;
import com.seijihe.rest_with_spring_boot.model.Person;
import com.seijihe.rest_with_spring_boot.repository.PersonRepository;


@Service 
public class PersonServiceV1 {

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.of(1, 1, 1);
    
    // private final AtomicLong counter = new AtomicLong();
    // @Autowired 
    // PersonRepository repository;

    private final PersonRepository repository;
    private final PersonDTOMapper mapper;

    public PersonServiceV1(PersonRepository repository, PersonDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    private Logger logger = LoggerFactory.getLogger(PersonServiceV1.class.getName());

    /*
        Mock: create structures of code to be used temporarily, and in the future when it
        not used anymore, can be deleted. 
    */

    public List<PersonDTO> findAll() {

        logger.info("Finding all people");

        // List<Person> people = new ArrayList<Person>();

        // Mock List Return
        // LongStream.rangeClosed(1, 5)
        //     .forEach(a -> {
        //         Person person = new Person();
        //         person.setId(a);
        //         person.setName("Person - " + a);
        //         person.setCompany("Company - " + a);
        //         person.setAge((int) a);
        //         people.add(person);
        //     });
        
        List<PersonDTO> people = mapper.personListToPersonDTOList(repository.findAll());
        
        people.forEach(this::addHateoasLinks); // people.forEach(p -> addHateoasLinks(p)); - The same
        
        return people;
        
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
        
        PersonDTO dto = mapper.personToPersonDTO(person);

        addHateoasLinks(dto);

        return dto;

    }

    public PersonDTO create(PersonDTO personDTO) {

        logger.info("Creating a person");
        
        Person person = mapper.personDTOToPerson(personDTO);
        person.setBirthday(DEFAULT_BIRTHDAY);

        Person saved = repository.save(person);

        PersonDTO dto = mapper.personToPersonDTO(saved);

        addHateoasLinks(dto);
        
        return dto;

    }

    public PersonDTO update(PersonDTO personDTO) {

        logger.info("Updating a person");

        Person person = mapper.personDTOToPerson(personDTO);

        Person entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));

        entity.setName(person.getName());
        entity.setAge(person.getAge());
        entity.setCompany(person.getCompany());

        Person saved = repository.save(entity);
        
        PersonDTO dto = mapper.personToPersonDTO(saved);
        
        addHateoasLinks(dto);

        return dto;
        
    }

    public void delete(Long id) {

        logger.info("Deleting a person");

        Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));

        repository.delete(entity);

    }

    private void addHateoasLinks(PersonDTO dto) {

        dto.add(linkTo(methodOn(PersonControllerV1.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonControllerV1.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonControllerV1.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonControllerV1.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(PersonControllerV1.class).slash(dto.getId()).withRel("delete").withType("DELETE")); //path-based link builder that works for void endpoints
        
    }

}
