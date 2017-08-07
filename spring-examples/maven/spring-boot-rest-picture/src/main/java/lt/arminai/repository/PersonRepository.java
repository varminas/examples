package lt.arminai.repository;

import lt.arminai.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vytautas Arminas
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
