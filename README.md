## Java Spring Framework 101

The ideia of this project is just introduction annotations about Spring Framework. 

1. [Spring Ecosystem](#spring-ecosystem)
2. [Spring Beans Annotations](#spring-beans-annotations)
3. [Annotations at @RestController](#annotations-at-restcontroller)
4. [SLF4J](#slf4j)
5. [DTO's](#dtos)
6. [API Versioning](#api-versioning)
7. [Migrations](#migrations)
8. [Custom Json Serialization](#custom-json-serialization)
9. [Content Negotiation](#content-negotiation)
10. [HATEOAS](#hateoas)

---

### Spring Ecosystem 

- Spring Framework
    - Dependency Injection (DI) and Inversion of Control (IoC), promoting abstraction and loose coupling between components
    - Modular architecture with extensible components and integration points

- Spring Boot
    - Convention over configuration: sensible defaults reduce boilerplate and minimize manual setup for common scenarios
    - Standalone application packaging with an embedded server runtime (e.g TomCat)
    - Starter dependencies (at Maven) and Actuator endpoints for application monitoring and health checks

- Spring Data
    - JDBC: database connectivity and SQL execution
    - Hibernate/JPA: object-relational mapping and query execution
    - Repositories: interfaces that encapsulate common persistence operations, allowing CRUD methods to be inherited by default
    - Query methods: method-name-based query derivation (e.g "findByName")
    - @Transactional: ensures atomicity and consistency for transactional operations

---

### Spring Beans Annotations

```
@RestController
@Service
@Autowired
@Configuration // Class that contains beans declarations
```

### Annotations at @RestController

To collect request information

```
@RequestParam() - /path?var01=something&var02=another
@PathVariable() - /path/{id}
@RequestBody() - json body
```

Example:

```java
@RestController
@RequestMapping("/path")
public class {

    @RequestMapping(
        value = "/path",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity method01(
        @RequestParam("variable01") String variable01,
        @RequestParam("variable02") String variable02
    ) {
        return new Something(variable01, variable02);
    }

    @RequestMapping(
        value = "/path",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity method(@PathVariable("variable") String variable) {
        return new Something(variable);
    }

    @RequestMapping(
        value = "/path",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity method(@RequestBody("variable") String variable) {
        return new Something(variable);
    }
}
```

---

### SLF4J

Log Hierarch:

> Trace > Debug > Info > Warn > Error

SLF4J (Simple Log Facade for Java): just an interface, the implementation can be something as Logback. That happens because you can change the implemetation until remove the methods distributed across your code. 

I can also create `application-hom.yml`, `application-prod.yml` per environment, and call it at terminal

```bash
java -jar app.jar --spring.profiles.active=prod
```

---

### DTO's

---

### API Versioning

- Approach that we'll use here: Versioning by path
    - http://localhost:8080/person
    - http://localhost:8080/v2/person

---

### Migrations

- Evolucionary Database Design: versioning an Database [[link](https://www.martinfowler.com/articles/evodb.html)]
- Name: PrefixVersion__Description_of_table.sql
    - Prefix: `V` (versioned) | `U` (Undo)
- At Maven projects: src/main/resources/db/migration

---

### Custom Json Serialization

- Serialization is the process of converting an object or data structure into a format that can be stored or transmitted over a network and later reconstructed.
    - Defines the order of elements in a JSON structure - `@JsonPropertyOrder`
    - Formats fields (e.g., converting text to uppercase or formatting dates) - `@JsonFormat`
    - Excludes fields that should not be exposed (e.g., sensitive data). - `@JsonFilter`, `@JsonIgnore`
    - This is important because serialization determines how data is represented and delivered to clients.
    - Are configurable aspects of serialization, not mandatory steps.
- The serialized format can be binary, JSON, XML, or another representation.
- Deserialization is the reverse process: converting serialized data (e.g., JSON) back into an object.
- In Spring Boot, these behaviors can be customized using Jackson annotations
- `@RequestBody` tells Spring to read the HTTP request body and deserialize its content into a Java object.
- `@RestController` tells Spring to write returned objects into the HTTP response body, typically serialized as JSON.

---

### Content Negotiation

- It is the ability to provide different representations of the same resource, delivering data in the format most suitable for the client (e.g., JSON, XML, YAML, CSV, PDF).
- This allows the system to meet different integration requirements, such as supporting legacy and modern systems.
- The response language can also be negotiated (e.g., English, Portuguese).
- Server-Driven vs. Agent-Driven:
    - Server-Driven: The server selects the most appropriate representation based on the client's preferences (e.g., Accept and Accept-Language headers).
    - Agent-Driven: The client selects the desired representation from the alternatives provided by the server, depending on the available formats.

---

### HATEOAS

- HATEOAS (Hypermedia as the Engine of Application State) is a REST principle where the server provides hyperlinks that guide clients through available actions and state transitions.
- How does hypermedia drive application state? The links change according to the resource's current state, telling the client what it can do next.
    - `CREATED`: provides `pay` and `cancel` links.
    - `PAID`: provides `track` and `refund` links.
    - `CANCELLED`: provides only a `self` link.
- `Key distinction`: Hypermedia does not directly change the state. It guides the client toward actions that can trigger state transitions.
- `Key benefit`: Clients discover available actions through API responses instead of hardcoding resource URLs.
- It's possible add the logic both on Controller or Service. However, since there is a lot of code at Controller, in our case we can add at Service to be more clean.
- For our object that will be serialized as response body - in this case PersonDTO -, we'll add at the class `extends RepresentationModel<PersonDTO>`, like the example below:

    ```java
    // That is the Base class for DTOs to collect links Hateoas

    import org.springframework.hateoas.RepresentationModel<T>

    public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {...}
    ```
- Then, at our PersonServiceV1 we attach
    ```java
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

    private void addHateoasLinks(PersonDTO dto) {

        dto.add(linkTo(methodOn(PersonControllerV1.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonControllerV1.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonControllerV1.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonControllerV1.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(PersonControllerV1.class).slash(dto.getId()).withRel("delete").withType("DELETE")); 
        
    }
    ```

- Returning the extended new DTOs containing the Hateoas links inside
    ```java
    public PersonDTO findById(Long id) {

        Person person = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records founded for this ID"));
        
        PersonDTO dto = mapper.personToPersonDTO(person);

        addHateoasLinks(dto);

        return dto;

    }
    ```

---

### Personal Annotations

1. Mock Development is a good way to structure methods relations util add the logic implementation inside them. 

