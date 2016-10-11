package lt.arminai.reactive.transformation.buffer;

import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimeTicker;

import java.util.concurrent.TimeUnit;

/**
 * Created by vytautas on 2016-10-08.
 */
public class BufferExample {
    public static void main(String[] args) {

        // 10 times per second of every 100ms
        TimeTicker timeTicker = new TimeTicker(100);
        timeTicker.start();

        timeTicker.toObservable()
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(
                        (list) -> {
                            System.out.println("--------------------------");

                            int count = 1;
                            int size = list.size();
                            for (int i= 0; i < size; i++) {
                                System.out.println("" + (count++) + ": " + list.get(i));
                            }
                        }
                );

        ThreadUtils.sleep(5000);

        timeTicker.stop();

        System.exit(0);
    }
}
