package lt.arminai.reactive.filtering;

import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimeTicker;

import java.util.concurrent.TimeUnit;

/**
 * Created by vytautas on 2016-10-08.
 */
public class TimeBasedExampleTimeout {
    public static void main(String[] args) {
        TimeTicker ticker = new TimeTicker(100);
        ticker.start();

        try {
            ticker.toObservable()
                    .timeout(3, TimeUnit.SECONDS)
                    .subscribe(
                            (t) -> {
                                System.out.println("Tick: " + t);
                            },
                            (exception) -> {
                                System.out.println("TIMEOUT");
                                exception.printStackTrace();
                            }
                    );
            ThreadUtils.sleep(1000);

            System.out.println("Pausing ticker.");
            ticker.pause();

            ThreadUtils.sleep(5000);
        } finally {
            ticker.stop();
        }

        System.exit(0);
    }
}
