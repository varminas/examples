package lt.arminai.service;

import lt.arminai.entity.Person;

/**
 * @author Vytautas Arminas
 */
public interface PersonService {
    Person get(long id);

    Person save(Person person);
}
