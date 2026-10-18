package com.seijihe.rest_with_spring_boot.dto.v1;

import java.util.List;
import org.mapstruct.Mapper;
import com.seijihe.rest_with_spring_boot.model.Person;

@Mapper(componentModel = "spring") // Until that annotation, won't create the bean
public interface PersonDTOMapper {

    PersonDTO personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTO personDTO);
    List<PersonDTO> personListToPersonDTOList(List<Person> person);

}
