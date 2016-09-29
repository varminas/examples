package lt.arminai.reactive.filtering;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by vytautas on 2016-09-29.
 */
public class PredicatesExample {
    public static void main(String[] args) {
        Observable.from(DataGenerator.generateBigIntegerList())
                .filter(i -> (i % 3 == 0) && (i < 20))
                .subscribe(
                        i -> System.out.println(i)
                );
        System.exit(0);
    }

}
