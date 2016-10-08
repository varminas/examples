package lt.arminai.reactive.transformation.mapping;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by vytautas on 2016-10-08.
 */
public class MapExample {
    public static void main(String[] args) {
        Observable.from(DataGenerator.generateGreekAlphabet())
                .map((letterString) ->
                    letterString.toUpperCase())
                .subscribe((letterString) -> {
                    System.out.println(letterString);
                });

        System.out.println("------------------------------------------------------");

        Observable.from(DataGenerator.generateGreekAlphabet())
                .flatMap((letterString) -> {
                    String[] returnString = {letterString.toUpperCase(), letterString.toLowerCase()};

                    return Observable.from(returnString);
                })

                .subscribe((letterString) -> {
                    System.out.println(letterString);
                });

        System.exit(0);
    }
}
