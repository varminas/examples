package lt.arminai.listfiltering;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        App app = new App();
        List<Animal> list = app.getList();

        Instant start = Instant.now();
        String name = list.stream()
                .filter(item -> item.getType().equals("Cat"))
                .findFirst()
                .map(Animal::getName)
                .orElse(null);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        System.out.println("=======================================================================");
        System.out.println("First findFirst(). \t Name: " + name + ";\t List size: " + list.size() + ";\t time: " + (duration.toMillis()) + "ms");

        start = Instant.now();
        name = list.stream()
                .filter(item -> item.getType().equals("Cat"))
                .map(Animal::getName)
                .findFirst()
                .orElse(null);
        end = Instant.now();
        Duration duration2 = Duration.between(start, end);

        System.out.println("First map(). \t\t Name: " + name + ";\t List size: " + list.size() + ";\t time: " + (duration2.toMillis()) + "ms");
        System.out.println("First filtering is faster ~" + (duration.toMillis()/duration2.toMillis()) + " times");
        System.out.println("=======================================================================");

    }

    private List<Animal> getList() {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("Jerry", "Mouse"));
        list.add(new Animal("Catty1", "Cat"));
        list.add(new Animal("Catty2", "Cat"));
        list.add(new Animal("Rex", "Dog"));

        IntStream.range(1, 10000).forEach(i -> list.add(new Animal("Name" + i, "type" + i)));

        return list;
    }
}
