package com.seijihe.rest_with_spring_boot.dto;

import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.seijihe.rest_with_spring_boot.model.Person;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    PersonDTO personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTO personDTO);
    List<PersonDTO> personListToPersonDTOList(List<Person> person);

}
