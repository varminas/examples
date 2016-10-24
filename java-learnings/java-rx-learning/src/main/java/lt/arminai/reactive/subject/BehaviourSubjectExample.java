package lt.arminai.reactive.subject;

import lt.arminai.helper.DataGenerator;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by vytautas on 2016-10-24.
 */
public class BehaviourSubjectExample {
    public static void main(String[] args) {


        /**
         * Publish subjects are good for multicasting events to subscribers.
         * The subscribers will see only the events that happen AFTER they subscribe.
         */
        BehaviorSubject<String> subject = BehaviorSubject.create("Start state");

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
                            System.out.println("onCompleted()");
                            subject.onCompleted();
                        }
                );

        subject.subscribe(
                (letter) -> {
                    System.out.println("Subscriber 2: " + letter);
                },
                (t) -> {
                    subject.onError(t);
                },
                () -> {
                    System.out.println("Subscriber 2: onCompleted");
                }
        );
    }

}
