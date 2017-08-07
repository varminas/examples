package lt.arminai.service;

import lt.arminai.entity.Person;
import lt.arminai.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vytautas Arminas
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person get(long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
