## Java Spring Framework 101

The ideia of this project is just introduction annotations about Spring Framework. 

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

### API Versioning

Versioning by path

---

### Personal Annotations

1. Mock Development is a good way to structure methods relations util add the logic implementation inside them. 

