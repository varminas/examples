package lt.arminai.reactive.transformation.conditions;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * @author Vytautas Arminas
 */
public class GeneralConditionalsExample {
    public static void main(String[] args) {

        Observable.empty()
                .defaultIfEmpty("Hello world")
                .subscribe((s) -> {
                    System.out.println(s);
                });

        System.out.println();

        Observable.from(DataGenerator.generateGreekAlphabet())
                .defaultIfEmpty("Hello world")
                .first()
                .subscribe((s) -> {
                    System.out.println(s);
                });

        System.out.println();

        Observable.from(DataGenerator.generateFibonacciList())
                .skipWhile((i) -> i < 8)
                .subscribe((i) -> {
                    System.out.println(i);
                });

        System.out.println();


        Observable.from(DataGenerator.generateFibonacciList())
                .takeWhile((i) -> i < 8)
                .subscribe((i) -> {
                    System.out.println(i);
                });

        System.out.println();
    }
}
