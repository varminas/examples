package lt.arminai.reactive.m3;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

import java.util.List;


/**
 * Created by Vytautas on 2016-03-07.
 */
public class SubscribtionAllOneThreadExample {
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Creating an Observable that does not specify a subscribeOn or an observeOn Scheduler");
        System.out.println("Driving thread: " + Thread.currentThread().getName());
        System.out.println("------------------------------------------------------------------------------------------");

        List<Integer> list = DataGenerator.generateFibonacciList();

        Observable<Integer> observable = Observable.from(list);

        observable.subscribe(
                // oneNext function
                (i) -> {
                    System.out.println("onNext thread entr: " + Thread.currentThread().getName());
                    System.out.println(i);
                    System.out.println("onNext thread exit: " + Thread.currentThread().getName());
                },
                // onError function
                (t) -> t.printStackTrace(),
                // onCompleted function
                () -> System.out.println("onCompleted")

        );
    }
}
