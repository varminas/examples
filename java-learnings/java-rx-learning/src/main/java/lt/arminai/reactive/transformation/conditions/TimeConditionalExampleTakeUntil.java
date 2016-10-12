package lt.arminai.reactive.transformation.conditions;

import lt.arminai.helper.DataGenerator;
import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimeTicker;
import lt.arminai.helper.TimedEventSequence;

/**
 * @author Vytautas Arminas
 */
public class TimeConditionalExampleTakeUntil {
    public static void main(String[] args) {
        TimedEventSequence<String> sequence1 = new TimedEventSequence<>(DataGenerator.generateEnglishAlphabet(), 50);
        TimeTicker ticker = new TimeTicker(3000);

        sequence1.toObservable()
                .takeUntil(ticker.toObservable())
                .subscribe((s) -> {
                    System.out.println(s);
                });

        ticker.start();
        sequence1.start();

        ThreadUtils.sleep(6000);

        sequence1.stop();
        ticker.stop();
    }
}
