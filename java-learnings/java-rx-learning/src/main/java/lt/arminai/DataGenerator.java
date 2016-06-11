package lt.arminai;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vytautas on 2016-03-07.
 */
public class DataGenerator {
    public static List<Integer> generateFibonacciList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(8);

        return list;
    }

    public static Integer[] generateFibonacciArray() {
        Integer[] array = new Integer[]{1, 2, 3, 5, 8};
        return array;
    }
}
