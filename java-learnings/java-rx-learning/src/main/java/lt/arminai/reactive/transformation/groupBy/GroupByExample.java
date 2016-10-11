package lt.arminai.reactive.transformation.groupBy;

import lt.arminai.helper.DataGenerator;
import rx.Observable;

import java.util.ArrayList;

/**
 * Created by vytautas on 2016-10-08.
 */
public class GroupByExample {
    public static void main(String[] args) {
        Observable.from(DataGenerator.generateBigIntegerList())
                .groupBy((i) -> 0 == (i % 2) ? "EVEN" : "ODD")
                // Subscribe to the Observable<GroupedObservable<String, Integer>>
                .subscribe((groupList) -> {
                    System.out.println("Key: " + groupList.getKey() + "----------------------");

                    groupList.subscribe((x) -> {
                        System.out.println(groupList.getKey() + " " + x);
                    });
                });

        System.out.println("------------------------------------");

        final ArrayList<Integer> evenList = new ArrayList<>();
        final ArrayList<Integer> oddList = new ArrayList<>();

        Observable.from(DataGenerator.generateBigIntegerList())
                .groupBy((i) -> 0 == (i % 2) ? "EVEN" : "ODD")
                // Subscribe to the Observable<GroupedObservable<String, Integer>>
                .subscribe((groupList) -> {
                    groupList.subscribe((x) -> {

                        if (groupList.getKey().equals("EVEN")) {
                            evenList.add(x);
                        } else {
                            oddList.add(x);
                        }
                    });
                });

        System.out.println();
        System.out.println();

        System.out.println("even list---------------------------");
        evenList.forEach((i) -> System.out.println(i));

        System.out.println();
        System.out.println();

        System.out.println("odd list---------------------------");
        oddList.forEach((i) -> System.out.println(i));
    }
}
