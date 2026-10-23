package com.seijihe.rest_with_spring_boot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import com.seijihe.rest_with_spring_boot.dto.v1.PersonDTO;
import com.seijihe.rest_with_spring_boot.dto.v1.PersonDTOMapper;
import com.seijihe.rest_with_spring_boot.mock.MockPerson;
import com.seijihe.rest_with_spring_boot.model.Person;
import com.seijihe.rest_with_spring_boot.repository.PersonRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    private PersonServiceV1 service;

    @Mock
    PersonRepository repository;

    @Mock
    PersonDTOMapper mapper;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        service = new PersonServiceV1(repository, mapper);
    }

    @Test
    void findById() {

        Person person = input.mockEntity(1);
        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(mapper.personToPersonDTO(person)).thenReturn(dto);

        PersonDTO result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/v1/person/1")
                && link.getType().equals("GET")
            )
        );
    }

    @Test
    void create() {

        Person person = input.mockEntity(1);
        PersonDTO dto = input.mockDTO(1);

        when(mapper.personDTOToPerson(dto)).thenReturn(person);
        when(repository.save(person)).thenReturn(person);
        when(mapper.personToPersonDTO(person)).thenReturn(dto);

        PersonDTO result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals(matchLinks(result.getLinks()), true);

    }

    @Test
    void update() {

        Person person = input.mockEntity(1);
        PersonDTO dto = input.mockDTO(1);

        when(mapper.personDTOToPerson(dto)).thenReturn(person);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(person);
        when(mapper.personToPersonDTO(person)).thenReturn(dto);

        PersonDTO result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals(matchLinks(result.getLinks()), true);

    }

    @Test
    void delete() {

        Person person = input.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        service.delete(1L);

    }

    boolean matchLinks(Links links) {

        List<Link> linkList = links.toList();

        linkList.remove(matchLinksConditions(linkList, "self", "/api/v1/person/1", "GET"));
        linkList.remove(matchLinksConditions(linkList, "findAll", "/api/v1/person", "GET"));
        linkList.remove(matchLinksConditions(linkList, "create", "/api/v1/person", "POST"));
        linkList.remove(matchLinksConditions(linkList, "update", "/api/v1/person", "PUT"));
        linkList.remove(matchLinksConditions(linkList, "delete", "/api/v1/person/1", "DELETE"));

        return true;
        
    }

    Link matchLinksConditions(List<Link> links, String relation, String path, String httpVerb) {

        Optional<Link> optional = links.stream().filter(link -> link.getRel().value().equals(relation)
                && link.getHref().endsWith(path)
                && link.getType().equals(httpVerb)).findFirst();

        return optional.get();

    }

}
