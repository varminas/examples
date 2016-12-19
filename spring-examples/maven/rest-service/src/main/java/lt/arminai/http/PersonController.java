package lt.arminai.http;

import lt.arminai.entity.Person;
import lt.arminai.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vytautas Arminas
 */
@RestController
@RequestMapping(value = "v1/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ExternalPerson> get(@PathVariable("id") long id) {
        LOGGER.debug("get() request received. Id={}", id);

        Person person = personService.get(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ExternalPerson(person), HttpStatus.OK);
    }

    @PostMapping
    public Person save(@RequestBody ExternalPerson person) {
        LOGGER.debug("save() request received. {}", person);

        return personService.save(person.toPerson());
    }
}
