package lt.arminai;

import lt.arminai.model.User;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Vytautas on 2015-11-17.
 */
public class App {

    private static EntityManager entityManager;

    public static void main(String[] args) {
        System.out.println("Starting application");
        entityManager = PersistenceManager.INSTANCE.getEntityManager();

        createUser("Vytautas", 30);

        getUsers().stream().forEach(System.out::println);
        entityManager.close();
        PersistenceManager.INSTANCE.close();
    }

    private static void createUser(String userName, int age) {
        User user = new User(userName, age);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    private static List<User> getUsers() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        entityManager.getTransaction().commit();
        return users;
    }
}
