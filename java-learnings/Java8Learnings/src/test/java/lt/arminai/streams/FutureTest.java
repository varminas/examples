package lt.arminai.streams;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by Vytautas on 2016-01-26.
 */
public class FutureTest {
    @Test
    public void testListenableFuture() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("called 1");
                IntStream.range(1, 1000000).forEach(i -> {
                    System.out.println(i * Math.sqrt(i) + Math.sqrt(i*i) * Math.abs(i +1 *2 /5) + i * Math.sqrt(i) + Math.sqrt(i*i) * Math.abs(i +1 *2 /5));
                });
                System.out.println("called 2");
                return 5;
            }
        });
        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println("OK " + integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failed");
            }
        });
    }
}
