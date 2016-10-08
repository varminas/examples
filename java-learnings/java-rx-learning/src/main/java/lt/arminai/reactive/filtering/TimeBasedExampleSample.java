package lt.arminai.reactive.filtering;

import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimeTicker;

import java.util.concurrent.TimeUnit;

/**
 * Created by vytautas on 2016-10-08.
 */
public class TimeBasedExampleSample {
    public static void main(String[] args) {
        TimeTicker ticker = new TimeTicker(10);
        ticker.start();

        try {
            ticker.toObservable()
                    .sample(1, TimeUnit.SECONDS)
                    .subscribe((t) -> {
                        System.out.println("Tick: " + t);
                    });
            ThreadUtils.sleep(10000);
        } finally {
            ticker.stop();
        }

        System.exit(0);
    }
}
