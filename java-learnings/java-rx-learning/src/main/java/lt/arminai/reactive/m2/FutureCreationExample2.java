package lt.arminai.reactive.m2;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by vytautas on 2016-09-29.
 */
public class FutureCreationExample2 {
    public static void main(String[] args) throws InterruptedException {
        FutureTask<List<String>> future = new FutureTask<>(() -> getStudentAslist());

        Scheduler.Worker worker = Schedulers.io().createWorker();
        worker.schedule(future::run);

        Observable.from(future)
                .subscribeOn(Schedulers.computation())
//                .flatMapIterable(s -> s)
                .subscribe(System.out::println);
        System.out.println("marker");

        TimeUnit.SECONDS.sleep(3);
    }

    public static List<String> getStudentAslist(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<String> collection = new ArrayList<>();
        collection.add("ONE");
        collection.add("Two");

        return collection;
    }
}
