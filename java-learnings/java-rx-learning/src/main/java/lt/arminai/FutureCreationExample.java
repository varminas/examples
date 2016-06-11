package lt.arminai;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by Vytautas on 2016-03-07.
 */
public class FutureCreationExample {
    public static void main(String[] args) {
        Observable<List<Integer>> observableFutureList;

        FutureTask<List<Integer>> future = new FutureTask<>(() -> DataGenerator.generateFibonacciList());
        observableFutureList = Observable.from(future);

        Schedulers.computation().now();

        observableFutureList.subscribe((list) ->
        list.forEach((i) -> System.out.println(i)));
    }
}
