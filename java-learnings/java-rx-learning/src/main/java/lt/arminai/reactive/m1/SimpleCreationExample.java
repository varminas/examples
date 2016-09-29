package lt.arminai.reactive.m1;


import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by Vytautas on 2016-03-07.
 */
public class SimpleCreationExample {
    public static void main(String[] args) {
        Observable<Integer> observable;

        System.out.println("Observable creation from the single value:");
        observable = Observable.just(Integer.valueOf(5));
        observable.subscribe((i) -> System.out.println(i));

        System.out.println("Observable creation from the Iterable:");
        observable = Observable.from(DataGenerator.generateFibonacciList());
        observable.subscribe((i) -> System.out.println(i));

        System.out.println("Observable creation from the Array:");
        observable = Observable.from(DataGenerator.generateFibonacciArray());
        observable.subscribe((i) -> System.out.println(i));
    }
}
