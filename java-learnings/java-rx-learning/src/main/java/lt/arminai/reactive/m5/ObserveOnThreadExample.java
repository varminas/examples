package lt.arminai.reactive.m5;

import lt.arminai.helper.DataGenerator;
import lt.arminai.helper.ThreadUtils;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;


/**
 * Created by Vytautas on 2016-03-07.
 */
public class ObserveOnThreadExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Creating an Observable that specifies a observeOn but not an subscribeOn Scheduler");
        System.out.println("Driving thread: " + Thread.currentThread().getName());
        System.out.println("------------------------------------------------------------------------------------------");

        // Create and sync  on an object that we will use to make sure we don't
        // hit the System.exit(0) call before our threads have had a chance to complete
        Object monitorObject = new Object();
        synchronized (monitorObject) {

            List<Integer> list = DataGenerator.generateFibonacciList();

            Observable<Integer> observable = Observable.from(list);

            observable
                    .observeOn(Schedulers.io())
                    .subscribe(
                            (i) -> {
                                System.out.println("onNext thread entr: " + Thread.currentThread().getName());
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
            ThreadUtils.wait(monitorObject);
        }
        System.exit(0);
    }
}
