package lt.arminai.reactive.composition;

import lt.arminai.helper.ThreadUtils;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by vytautas on 2016-09-29.
 */
public class CompostionExample {
    public static void main(String[] args) {
        Object objectMonitor = new Object();
        synchronized (objectMonitor) {
            UserService userService = new UserService();

            System.out.println("{ \"userList\" :[ ");

            Observable.from(userService.fetchUserList())
                    .filter(user -> user.getSecurityStatus() != SecurityStatus.ADMINISTRATOR)
                    .toSortedList((user1, user2) -> user1.getSecurityStatus().compareTo(user2.getSecurityStatus()))
                    .subscribeOn(Schedulers.io())
                    .doOnCompleted(() -> {
                        synchronized (objectMonitor) {
                            objectMonitor.notify();
                        }
                    })
                    .subscribe(
                            users -> {
                                users.forEach(user -> System.out.println(user.toJSON()));
                            }
                    );
            ThreadUtils.wait(objectMonitor);
            System.out.println("]}");
        }

        System.exit(0 );
    }
}
