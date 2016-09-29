package lt.arminai.reactive.m2;

import lt.arminai.helper.DataGenerator;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Vytautas on 2016-03-07.
 */
public class FutureCreationExample {
    public static void main(String[] args) {
        Observable<List<Integer>> observableFutureList;
        System.out.println("Future example");

        FutureTask<List<Integer>> futureTask = new FutureTask<>(() -> DataGenerator.generateFibonacciList());

        observableFutureList = Observable.from(futureTask);

        Schedulers.computation().createWorker().schedule(() -> futureTask.run());

        observableFutureList.subscribe((list) ->
                list.forEach((i) -> System.out.println(i)));
    }
}
