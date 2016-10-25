package lt.arminai.reactive.eventdriven.userservice;

import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by vytautas on 2016-10-25.
 */
public class UserServiceImpl implements UserService {

    private final PublishSubject<UserEvent> userEventSubject;

    public UserServiceImpl() {
        this.userEventSubject = PublishSubject.create();
    }

    @Override
    public void addUser(String username, String emailAddress) {
        System.out.println("UserServiceImpl: addUser - " + username + ", " + emailAddress);

        UserEvent addUserEvent = new CreateUserEvent(username, emailAddress);

        userEventSubject.onNext(addUserEvent);
    }

    @Override
    public void subscribeToUserEvents(Observer<UserEvent> subscriber) {
        userEventSubject.subscribe(subscriber);

    }

    @Override
    public void subscribeToUserEvents(Action1<UserEvent> onNext) {
        userEventSubject.subscribe(onNext);
    }
}
