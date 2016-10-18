package lt.arminai.reactive.connectable;

import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimeTicker;
import rx.observables.ConnectableObservable;

/**
 * Created by vytautas on 2016-10-18.
 */
public class DualConnectableExample {
    public static void main(String[] args) {
        TimeTicker timeTicker = new TimeTicker(500);
        timeTicker.start();

        ConnectableObservable<Long> connectable = timeTicker
                .toObservable()
                .publish();

        connectable.subscribe((t) -> {
            System.out.println("Tick: " + ThreadUtils.currentThreadName() + " " + t);
        });

        connectable.subscribe((t) -> {
            System.out.println("Tick2: " + ThreadUtils.currentThreadName() + " " + t);
        });

        System.out.println("Sleeping for 3 seconds");
        ThreadUtils.sleep(3000);

        System.out.println("Connecting...");

        connectable.connect();

        System.out.println("3 seconds are up!");
        ThreadUtils.sleep(3000);

        timeTicker.stop();
    }
}
