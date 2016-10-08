package lt.arminai.reactive.filtering;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by vytautas on 2016-10-08.
 */
public class PositionExampleDistinct {
    public static void main(String[] args) {
            Observable.from(DataGenerator.generateScrumbleAndDuppedGreekAlphabet())
                    .subscribe((letter) -> {
                        System.out.println(letter);
                    });

            System.out.println("--------------------------------------");

        Observable.from(DataGenerator.generateScrumbleAndDuppedGreekAlphabet())
                .distinct()
                .subscribe((letter) -> {
                    System.out.println(letter);
                });
        System.out.println("--------------------------------------");

        System.exit(0);
    }
}
