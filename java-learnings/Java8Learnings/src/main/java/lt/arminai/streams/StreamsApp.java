package lt.arminai.streams;

import com.google.common.util.concurrent.*;
import javafx.scene.control.Button;
import lt.arminai.streams.entities.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Vytautas on 2015-09-13.
 */
public class StreamsApp {

    private static List<Transaction> transactions;
    static {
        transactions = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            transactions.add(new Transaction((int)(Math.random() * 100), "Tr" + (int)(Math.random() * 100)));
        }
    }
    public static void main(String[] args) {
        new Button().addEventHandler();

        new StreamsApp().testListenableFuture();

    }

    public void streamsExcercised() {
        System.out.println("Start of Streams Exercises");
        System.out.println("Initial transactions arrayL");
        transactions.forEach(t-> {
            System.out.print(t.getId() + " " + t.getName() + "; ");
        });

        System.out.println("\n Sorted stream");
        List<Transaction> sortedTrans;
        transactions.stream()
                .filter(t -> t.getId() > 50)
                .sorted(Comparator.comparing(Transaction::getId).reversed())
                .forEach(t -> System.out.println(t.getId() + " " + t.getName()));

        List<Integer> ids = transactions.stream()
                .filter(t -> t.getId() > 50)
                .sorted(comparing(Transaction::getId).reversed())
                .map(Transaction::getId)
                .collect(toList());
        ids.forEach(System.out::println);
    }

    public void testListenableFuture() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("called 1");
                IntStream.range(1, 1000000).forEach(i -> {
                    System.out.println(i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5) + i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5));
                    System.out.println(i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5) + i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5));
                    System.out.println(i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5) + i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5));
                    System.out.println(i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5) + i * Math.sqrt(i) + Math.sqrt(i) * Math.abs(i +1 *2 /5));
                });
                System.out.println("called 2");
                return 5;
            }
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        Futures.addCallback(future, new FutureCallback<Integer>() {
//            @Override
//            public void onSuccess(Integer integer) {
//                System.out.println("OK " + integer);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                System.out.println("failed");
//            }
//        });
    }
}
