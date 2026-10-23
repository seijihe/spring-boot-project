package com.seijihe.rest_with_spring_boot.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import com.seijihe.rest_with_spring_boot.dto.v1.PersonDTO;
import com.seijihe.rest_with_spring_boot.model.Person;

public class MockPerson {
    
    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> people = new ArrayList<Person>();
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

    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> people = new ArrayList<PersonDTO>();
        LongStream.rangeClosed(1, 5)
            .forEach(a -> {
                PersonDTO person = new PersonDTO();
                person.setId(a);
                person.setName("Person - " + a);
                person.setCompany("Company - " + a);
                person.setAge((int) a);
                people.add(person);
            });

        return people;
    }

    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setId(number.longValue());
        person.setName("Person - " + number);
        person.setCompany("Company - " + number);
        person.setAge((int) number);
        return person;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setId(number.longValue());
        person.setName("Person - " + number);
        person.setCompany("Company - " + number);
        person.setAge((int) number);
        return person;
    }

}
