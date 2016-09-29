package lt.arminai.functions;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Vytautas on 2016-02-22.
 */
public class HigherOrderFunctions {
    public static void main(String[] args) {
        System.out.println(
                createCombineAndTransform("vienas ", "du", (s) -> s.toUpperCase())
                .get());
    }

    public static Supplier<String> createCombineAndTransform(final String a, final String b,
                                                             Function<String, String> transform) {
        return () -> {
            String aa = a;
            String bb =b;
            if (transform != null) {
                aa = transform.apply(a);
                bb = transform.apply(b);
            }
            return aa + bb;
        };
    }
}
