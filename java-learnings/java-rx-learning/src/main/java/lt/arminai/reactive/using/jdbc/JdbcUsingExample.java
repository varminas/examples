package lt.arminai.reactive.using.jdbc;

import lt.arminai.helper.ThreadUtils;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by vytautas on 2016-10-18.
 */
public class JdbcUsingExample {
    public static void main(String[] args) {
        Action1<Throwable> simpleErrorHandler = (t) -> {
            t.printStackTrace();
        };

        TestDatabase.init();

        Func0<ConnectionSubscription> resourceFactory = () -> {
            return new ConnectionSubscription(TestDatabase.createConnection());
        };

        Func1<ConnectionSubscription, Observable<String>> greekAlphabetList = (connectionSubscription) -> {
            return TestDatabase.selectGreekAlphabet(connectionSubscription);
        };

        Action1<ConnectionSubscription> dispose = (connectionSubscription) -> {
            connectionSubscription.unsubscribe();
        };

        Observable<String> t = Observable.using(resourceFactory, greekAlphabetList, dispose);
        t.subscribe(
                (letter) -> {
                    System.out.println(ThreadUtils.currentThreadName() + " - " + letter);
                },
                simpleErrorHandler,
                () -> {
                    System.out.println("onCompleted");
                }
        );
    }
}
