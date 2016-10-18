package lt.arminai.reactive.connectable;

import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimeTicker;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Created by vytautas on 2016-10-18.
 */
public class SlowParallelObserveOnConnectableExample {
    public static void main(String[] args) {
        TimeTicker timeTicker = new TimeTicker(500);
        timeTicker.start();

        ConnectableObservable<Long> connectable = timeTicker
                .toObservable()
                .publish();

        connectable
                .observeOn(Schedulers.computation())
                .subscribe((t) -> {
                    System.out.println("Tick: " + ThreadUtils.currentThreadName() + " " + t);
                });

        connectable
                .observeOn(Schedulers.computation())
                .subscribe((t) -> {
                    System.out.println("Tick2: " + ThreadUtils.currentThreadName() + " " + t);
                    ThreadUtils.sleep(1000);
                });

        System.out.println("Sleeping for 3 seconds");
        ThreadUtils.sleep(3000);

        System.out.println("Connecting...");
        connectable.connect();

        ThreadUtils.sleep(5000);
        System.out.println("5 seconds are up! STOPPING THE TICKER");

        timeTicker.stop();

        ThreadUtils.sleep(5000);

        System.out.println("Notice how the second observer continued  to process  its  scheduled work");

    }
}
