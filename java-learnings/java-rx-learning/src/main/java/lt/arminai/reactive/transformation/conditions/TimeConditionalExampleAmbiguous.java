package lt.arminai.reactive.transformation.conditions;

import lt.arminai.helper.DataGenerator;
import lt.arminai.helper.ThreadUtils;
import lt.arminai.helper.TimedEventSequence;
import rx.Observable;

/**
 * @author Vytautas Arminas
 */
public class TimeConditionalExampleAmbiguous {
    public static void main(String[] args) {
        TimedEventSequence<String> sequence1 = new TimedEventSequence<>(DataGenerator.generateGreekAlphabet(), 50);
        TimedEventSequence<String> sequence2 = new TimedEventSequence<>(DataGenerator.generateEnglishAlphabet(), 100);

        Observable.amb(sequence1.toObservable(), sequence2.toObservable())
                .subscribe((s) -> {
                    System.out.println(s);
                });

        sequence1.start();
        sequence2.start();

        ThreadUtils.sleep(4000);

        sequence1.stop();
        sequence2.stop();
    }
}
