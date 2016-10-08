package lt.arminai.reactive.filtering;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by vytautas on 2016-10-08.
 */
public class PositionExampleElementAt {
    public static void main(String[] args) {
            Observable.from(DataGenerator.generateGreekAlphabet())
                    .elementAt(2)
                    .subscribe((letter) -> {
                        System.out.println(letter);
                    });

            System.out.println();

        Observable.from(DataGenerator.generateGreekAlphabet())
                .elementAtOrDefault(50, "Unknown")
                .subscribe((letter) -> {
                    System.out.println(letter);
                });
        System.out.println();

        System.exit(0);
    }
}
