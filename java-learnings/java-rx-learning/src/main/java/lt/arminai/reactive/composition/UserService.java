package lt.arminai.reactive.composition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vytautas on 2016-09-29.
 */
public class UserService {
    private final List<User> allUsers;

    public UserService() {
        allUsers = new ArrayList<>();

        allUsers.add(new User("sskyprancer", "scot.orr@sasa.su", SecurityStatus.GUEST));
        allUsers.add(new User("hmoto", "scot.orr@sasa.su", SecurityStatus.MODERATOR));
        allUsers.add(new User("jttent", "scot.orr@sasa.su", SecurityStatus.ADMINISTRATOR));
        allUsers.add(new User("dvader", "scot.orr@sasa.su", SecurityStatus.GUEST));
        allUsers.add(new User("lcarlosian", "scot.orr@sasa.su", SecurityStatus.MODERATOR));
    }

    public List<User> fetchUserList() {
        return Collections.unmodifiableList(allUsers);
    }
}
