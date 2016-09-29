package lt.arminai.reactive.composition;

/**
 * Created by vytautas on 2016-09-29.
 */
public class User {
    private final String username;
    private final String email;
    private final SecurityStatus securityStatus;

    public User(String username, String email, SecurityStatus securityStatus) {
        this.username = username;
        this.email = email;
        this.securityStatus = securityStatus;
    }

    public SecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String toJSON() {
        return "\"username\": \"" + username + "\", \"email\": \"" + email + "\", \"securityStatus\": \"" + securityStatus + "\" ";
    }
}
