package lt.arminai;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Vytautas on 2016-02-22.
 */
public class FirstClassCitizen {
    public static void main(String[] args) {
        BiFunction<String, String, String> concatFunction = (s, t) -> s + " " + t;

        System.out.println(concatFunction.apply("vienas", "du"));

        System.out.println(concatAndTransform("VIENAS", "du", (s) -> {
            return s.toLowerCase();
        }));

        System.out.println(concatAndTransform("vienaS", "Du", (s) -> {
            return s.toUpperCase();
        }));
    }

    public static String concatAndTransform(String a, String b, Function<String, String> stringTransform) {
        if (stringTransform != null) {
            a = stringTransform.apply(a);
            b = stringTransform.apply(b);
        }
        return a + b;
    }
}
