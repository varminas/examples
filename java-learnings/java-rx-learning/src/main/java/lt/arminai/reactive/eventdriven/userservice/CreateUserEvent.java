package lt.arminai.reactive.eventdriven.userservice;

/**
 * Created by vytautas on 2016-10-25.
 */
public class CreateUserEvent extends UserEvent {

    public CreateUserEvent(String username, String emailAddress) {
        super(username, emailAddress);
    }
}
