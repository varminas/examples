package lt.arminai.reactive.subject;

import lt.arminai.helper.DataGenerator;
import lt.arminai.helper.ThreadUtils;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by vytautas on 2016-10-24.
 */
public class PublishSubjectExample {
    public static void main(String[] args) {
        Object signal = new Object();
        synchronized (signal) {

            /**
             * Publish subjects are good for multicasting events to subscribers.
             * The subscribers will see only the events that happen AFTER they subscribe.
             */
            PublishSubject<String> subject = PublishSubject.create();

            subject.subscribe(
                    (letter) -> {
                        System.out.println("Subscriber 1: " + letter);

                        ThreadUtils.sleep(500);
                        if (letter.equals("Eta")) {
                            synchronized (signal) {
                                signal.notify();
                            }
                        }
                    }
            );

            Observable.from(DataGenerator.generateGreekAlphabet())
                    .subscribeOn(Schedulers.computation())
                    .subscribe(
                            (letter) -> {
                                subject.onNext(letter);
                            },
                            (t) -> {
                                subject.onError(t);
                            },
                            () -> {
                                System.out.println("Subscriber 1: onCompleted()");
                                subject.onCompleted();
                                synchronized (signal) {
                                    signal.notify();
                                }
                            }
                    );
            ThreadUtils.wait(signal);

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

            ThreadUtils.wait(signal);
        }
    }
}
