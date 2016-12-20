package lt.arminai.streams;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Vytautas on 2015-09-13.
 */
public class Application {

    private static Supplier<Integer> getIntValue = () -> {
        sleep(1);
        return 1;
    };

    private static Function<Integer, Integer> plusOne = (x) -> x + 1;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newSingleThreadExecutor();

        CompletableFuture<Integer> intValue1 = CompletableFuture.supplyAsync(getIntValue, exec);
        System.out.println("1 Is first done?: " + intValue1.isDone());

        CompletableFuture<Integer> intValue2 = intValue1.thenApply(plusOne);
        System.out.println("2 Is first done?: " + intValue1.isDone());

        System.out.println("First check: " + intValue2.get());
        System.out.println("3 Is first done?: " + intValue1.isDone());
        System.out.println("4 Is second done?: " + intValue2.isDone());

        System.exit(1);
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
