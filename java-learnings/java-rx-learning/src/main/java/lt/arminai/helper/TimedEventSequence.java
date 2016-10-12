package lt.arminai.helper;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.Iterator;
import java.util.List;

/**
 * Created by vytautas on 2016-10-12.
 */
public class TimedEventSequence<T> {
    private final BehaviorSubject<T> eventSubject;
    private final Iterator<T> list;
    private final long interval;
    private T currentItem;
    private Thread eventThread;
    private long lastTick;

    public TimedEventSequence(List<T> list, long interval) {
        eventThread = null;
        lastTick = System.currentTimeMillis();

        this.list = list.iterator();
        this.interval = interval;

        currentItem = this.list.hasNext() ? this.list.next() : null;
        eventSubject = BehaviorSubject.create(currentItem);
    }

    public Observable<T> toObservable() {
        return eventSubject;
    }

    public synchronized void start() {
        if (eventThread != null) {
            return;
        }

        eventThread = new Thread(() -> {
            try {
                while (eventThread.isInterrupted() == false) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        break;
                    }

                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTick > interval) {
                        lastTick = currentTime;
                        if (list.hasNext()) {
                            eventSubject.onNext(list.next());
                        }
                    }
                }
            } catch (Throwable e) {
                eventSubject.onError(e);
            }

            eventSubject.onCompleted();
        }, "TimedEventSequenceThread");
        eventThread.start();
    }

    public synchronized void stop() {
        if (eventThread == null) {
            return;
        }

        eventThread.interrupt();
        try {
            eventThread.join();
        } catch (InterruptedException e) {

        }
    }
}
