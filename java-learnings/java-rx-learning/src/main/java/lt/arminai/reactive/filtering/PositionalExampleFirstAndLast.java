package lt.arminai.reactive.filtering;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by vytautas on 2016-09-29.
 */
public class PositionalExampleFirstAndLast {
    public static void main(String[] args) {
        Observable.from(DataGenerator.generateGreekAlphabet())
                .first()
                .subscribe(
                        s -> System.out.println(s)
                );

        System.out.println("");

        Observable.from(DataGenerator.generateGreekAlphabet())
                .take(4)
                .subscribe(
                        s -> System.out.println(s)
                );

        System.out.println("");

        Observable.from(DataGenerator.generateGreekAlphabet())
                .last()
                .subscribe(
                        s -> System.out.println(s)
                );

        System.out.println("");

        Observable.from(DataGenerator.generateGreekAlphabet())
                .takeLast(4)
                .subscribe(
                        s -> System.out.println(s)
                );

        System.out.println("");

        Observable.empty()
                .firstOrDefault("List is empty")
                .subscribe(
                        s -> System.out.println(s)
                );

        Observable.empty()
                .lastOrDefault("List is empty")
                .subscribe(
                        s -> System.out.println(s)
                );

        System.exit(0);
    }
}
