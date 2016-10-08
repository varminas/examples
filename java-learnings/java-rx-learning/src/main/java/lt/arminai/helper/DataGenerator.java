package lt.arminai.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        list.add(13);
        list.add(21);
        list.add(34);
        list.add(55);

        return list;
    }

    public static Integer[] generateFibonacciArray() {
        Integer[] array = new Integer[]{1, 2, 3, 5, 8};
        return array;
    }

    public static List<Integer> generateBigIntegerList() {
        return IntStream.range(0, 199).boxed().collect(Collectors.toList());
    }

    public static List<String> generateGreekAlphabet() {
        List<String> list = new ArrayList<>();
        list.add("alpha");
        list.add("beta");
        list.add("gamma");
        list.add("delta");
        list.add("Phi");
        list.add("Chi");
        list.add("Psi");
        list.add("omega");

        return list;
    }

    public static List<String> generateScrumbleAndDuppedGreekAlphabet() {
        List<String> list = new ArrayList<>();
        list.addAll(generateGreekAlphabet());
        list.addAll(generateGreekAlphabet());
        list.addAll(generateGreekAlphabet());

        Collections.shuffle(list);
        return list;
    }
}
