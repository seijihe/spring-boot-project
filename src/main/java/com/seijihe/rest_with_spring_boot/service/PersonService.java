package com.seijihe.rest_with_spring_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.LongStream;
import org.springframework.stereotype.Service;
import com.seijihe.rest_with_spring_boot.model.Person;

@Service 
public class PersonService {
    
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    /*
        Mock: create structures of code to be used temporarily, and in the future when it
        not used anymore, can be deleted. 
    */

    public List<Person> findAll() {

        logger.info("Finding all people");

        List<Person> people = new ArrayList<Person>();

        // Mock List Return
        LongStream.rangeClosed(1, 5)
            .forEach(a -> {
                Person person = new Person();
                person.setId(a);
                person.setName("Person - " + a);
                person.setCompany("Company - " + a);
                person.setAge((int) a);
                people.add(person);
            });
        
        return people;

    }

    public Person findById(String id) {
        logger.info("Finding a Person at Database");

        Person person = new Person();

        person.setId(counter.incrementAndGet()); // Simulate persistency and return from Database
        person.setName("Steve Jobs");
        person.setCompany("Apple");
        person.setAge(56);

        return person;
    }

    public Person create(Person person) {

        logger.info("Creating a person");
        return person;

    }

    public Person update(Person person) {

        logger.info("Updating a person");
        return person;

    }

    public void delete(String id) {

        logger.info("Deleting a person");

    }
}
