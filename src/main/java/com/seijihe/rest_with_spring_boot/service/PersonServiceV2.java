package com.seijihe.rest_with_spring_boot.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.seijihe.rest_with_spring_boot.dto.v2.PersonDTOV2;
import com.seijihe.rest_with_spring_boot.dto.v2.PersonDTOV2Mapper;
import com.seijihe.rest_with_spring_boot.exception.ResourceNotFoundException;
import com.seijihe.rest_with_spring_boot.model.Person;
import com.seijihe.rest_with_spring_boot.repository.PersonRepository;

@Service 
public class PersonServiceV2 {

    private final PersonRepository repository;
    private final PersonDTOV2Mapper mapper;
    private Logger logger = LoggerFactory.getLogger(PersonServiceV2.class.getName());

    public PersonServiceV2(PersonRepository repository, PersonDTOV2Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PersonDTOV2> findAll() {

        logger.info("Finding all people");

        return mapper.personListToPersonDTOList(repository.findAll());
        
    }

    public PersonDTOV2 findById(Long id) {
        logger.info("Finding a Person at Database");

        Person person = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));
        
        return mapper.personToPersonDTO(person);

    }

    public PersonDTOV2 create(PersonDTOV2 personDTO) {

        logger.info("Creating a person");
        
        Person person = mapper.personDTOToPerson(personDTO);

        Person saved = repository.save(person);

        return mapper.personToPersonDTO(saved);

    }

    public PersonDTOV2 update(PersonDTOV2 personDTO) {

        logger.info("Updating a person");

        Person person = mapper.personDTOToPerson(personDTO);

        Person entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));

        entity.setName(person.getName());
        entity.setAge(person.getAge());
        entity.setCompany(person.getCompany());
        entity.setBirthday(person.getBirthday());

        Person saved = repository.save(entity);
        
        return mapper.personToPersonDTO(saved);
        
    }

    public void delete(Long id) {

        logger.info("Deleting a person");

        Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));

        repository.delete(entity);

    }
}
