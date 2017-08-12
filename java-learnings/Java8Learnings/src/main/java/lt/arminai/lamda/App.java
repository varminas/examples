package lt.arminai.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    private Service service;

    public App(Service service) {
        this.service = service;
    }

    public static void main(String[] args) {
        App app = new App(new Service());

        try {
            IntStream.range(0, 10).forEach(i -> {
                app.service.getName(i);
            });
        } catch (RuntimeException e) {
            System.out.println("Thrown exception 0");
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }

        try {
            List<String> collect = list.stream()
                    .map(i -> app.service.getName(i))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            System.out.println("Thrown exception 1");
        }
    }
}
