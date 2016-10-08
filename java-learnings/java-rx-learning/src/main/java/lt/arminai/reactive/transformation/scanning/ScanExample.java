package lt.arminai.reactive.transformation.scanning;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

/**
 * Created by vytautas on 2016-10-08.
 */
public class ScanExample {
    public static void main(String[] args) {
        Observable.from(DataGenerator.generateGreekAlphabet())
                .scan(new StringBuilder(), (accumBuffer, nextLetter) ->
                    accumBuffer.append(nextLetter)
                )
                .subscribe((total) -> {
                    System.out.println("Scan event:" + total.toString());
                });

        System.out.println("------------------------------------");

        Observable.from(DataGenerator.generateGreekAlphabet())
                .scan(new StringBuilder(), (accumBuffer, nextLetter) ->
                        accumBuffer.append(nextLetter)
                )
                .last()
                .subscribe((total) -> {
                    System.out.println("Scan event:" + total.toString());
                });
    }
}
