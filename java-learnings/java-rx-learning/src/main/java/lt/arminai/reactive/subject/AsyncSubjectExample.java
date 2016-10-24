package lt.arminai.reactive.subject;

import lt.arminai.helper.DataGenerator;
import rx.Observable;
import rx.subjects.AsyncSubject;

/**
 * Created by vytautas on 2016-10-24.
 */
public class AsyncSubjectExample {
    public static void main(String[] args) {

        /**
         * Publish subjects are good for multicasting events to subscribers.
         * The subscribers will see only the events that happen AFTER they subscribe.
         */
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.subscribe(
                (letter) -> {
                    System.out.println(letter);
                }
        );

        Observable.from(DataGenerator.generateGreekAlphabet())
                .subscribe(
                        (letter) -> {
                            subject.onNext(letter);
                        },
                        (t) -> {
                            subject.onError(t);
                        },
                        () -> {
                            subject.onCompleted();
                        }
                );
    }

}
