package lt.arminai.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        list.add("Alpha");
        list.add("Beta");
        list.add("Gamma");
        list.add("Delta");
        list.add("Epsilon");
        list.add("Zeta");
        list.add("Eta");
        list.add("Theta");
        list.add("Iota");
        list.add("Kappa");
        list.add("Lambda");
        list.add("Mu");
        list.add("Phi");
        list.add("Chi");
        list.add("Psi");
        list.add("Mmega");

        return list;
    }

    public static List<String> generateEnglishAlphabet() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("i");
        list.add("j");
        list.add("k");
        list.add("l");
        list.add("m");
        list.add("n");
        list.add("o");
        list.add("p");
        list.add("r");
        list.add("s");
        list.add("t");
        list.add("u");
        list.add("v");
        list.add("w");
        list.add("x");
        list.add("y");
        list.add("z");

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
