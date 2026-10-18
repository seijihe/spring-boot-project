package com.seijihe.rest_with_spring_boot.dto.v2;

import java.util.List;
import org.mapstruct.Mapper;

import com.seijihe.rest_with_spring_boot.model.Person;

@Mapper(componentModel = "spring")
public interface PersonDTOV2Mapper {

    PersonDTOV2 personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTOV2 personDTO);
    List<PersonDTOV2> personListToPersonDTOList(List<Person> person);

}
