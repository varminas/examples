package lt.arminai.http;

import lt.arminai.entity.Person;

/**
 * @author Vytautas Arminas
 */
public class ExternalPerson {
    private Long id;
    private String name;
    private int age;

    private ExternalPerson() {
    }

    public ExternalPerson(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person toPerson() {
        return new Person(name, age);
    }

    @Override
    public String toString() {
        return "ExternalPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
