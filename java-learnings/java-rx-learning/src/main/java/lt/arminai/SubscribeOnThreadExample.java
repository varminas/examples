package lt.arminai;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;


/**
 * Created by Vytautas on 2016-03-07.
 */
public class SubscribeOnThreadExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Driving thread: " + Thread.currentThread().getName());

        Object monitorObject = new Object();
        synchronized (monitorObject) {

            List<Integer> list = DataGenerator.generateFibonacciList();

            Observable<Integer> observable = Observable.from(list);

            observable.subscribeOn(Schedulers.newThread())
                    .subscribe(
                            (i) -> {
                                System.out.println("onNext thread enter: " + Thread.currentThread().getName());
                                System.out.println(i);
                                System.out.println("onNext thread exit: " + Thread.currentThread().getName());
                            },
                            (t) -> t.printStackTrace(),
                            () -> {
                                System.out.println("onCompleted");
                                synchronized (monitorObject) {
                                    monitorObject.notify();
                                }
                            }
                    );
            Thread.currentThread().wait(1);
        }
    }
}
