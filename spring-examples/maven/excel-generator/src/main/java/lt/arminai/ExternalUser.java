package lt.arminai;

/**
 * Created by vytautas on 17.6.7.
 */
public class ExternalUser {
    private String name;
    private Integer age;

    private ExternalUser() {
    }

    public ExternalUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ExternalUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
