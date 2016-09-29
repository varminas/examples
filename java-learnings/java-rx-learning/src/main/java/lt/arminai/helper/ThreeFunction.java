package lt.arminai.helper;

/**
 * Created by vytautas on 2016-06-11.
 */
@FunctionalInterface
public interface ThreeFunction<T, S, U, R> {
    R apply(T t, S s, U u);
}
