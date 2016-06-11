package lt.arminai;

import rx.Observable;

import java.util.List;


/**
 * Created by Vytautas on 2016-03-07.
 */
public class SubscribtionAllOneThreadExample {
    public static void main(String[] args) {
        System.out.println("Driving thread: " + Thread.currentThread().getName());

        List<Integer> list = DataGenerator.generateFibonacciList();

        Observable<Integer> observable = Observable.from(list);

        observable.subscribe(
                (i) -> {
                    System.out.println("onNext thread enter: " + Thread.currentThread().getName());
                    System.out.println(i);
                    System.out.println("onNext thread exit: " + Thread.currentThread().getName());
                },
                (t) -> t.printStackTrace(),
                () -> System.out.println("onCompleted")

        );
    }
}
