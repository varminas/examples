package lt.arminai.http;

import lt.arminai.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vytautas Arminas
 */
@RestController
@RequestMapping(value = "v1/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping(value = "{id}")
    public Person get(@PathVariable("id") long id) {
        LOGGER.debug("get() request received. Id={}", id);

        return new Person(123L, "Name", 22);
    }
}
