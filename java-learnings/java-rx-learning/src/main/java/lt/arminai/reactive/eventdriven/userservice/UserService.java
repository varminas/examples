package lt.arminai.reactive.eventdriven.userservice;

import rx.Observer;
import rx.functions.Action1;


/**
 * Created by vytautas on 2016-10-25.
 */
public interface UserService {
    void addUser(String usernam, String emailAddress);

    void subscribeToUserEvents(Observer<UserEvent> subscriber);

    void subscribeToUserEvents(Action1<UserEvent> onNext);
}
