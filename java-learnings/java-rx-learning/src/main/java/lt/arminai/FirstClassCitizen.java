package lt.arminai;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Vytautas on 2016-02-22.
 */
public class FirstClassCitizen {
    public static void main(String[] args) {
        BiFunction<String, String, String> concatFunction = (s, t) -> s + "; " + t;

        System.out.println(concatFunction.apply("vienas", "du"));

        System.out.println(concatAndTransform("VIENAS", "du", (s) -> {
            return s.toLowerCase();
        }));

        System.out.println(concatAndTransform("vienaS", "Du", (s) -> {
            return s.toUpperCase();
        }));

        concatFunction = FirstClassCitizen::concatString;
        System.out.println(concatFunction.apply("vytautas", "arminas"));
    }

    public static String concatAndTransform(String a, String b, Function<String, String> stringTransform) {
        if (stringTransform != null) {
            a = stringTransform.apply(a);
            b = stringTransform.apply(b);
        }
        return a + b;
    }

    public static String concatString(String s1, String s2) {
        return s1 + s2;
    }

    public static String concat3String(String s1, String s2, String s3) {
        return s1 + s2 + s3;
    }
}
