package sample.principal;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by vytautas on 17.6.3.
 */
public class MyPrincipal implements Principal, Serializable {
    private final String username;

    public MyPrincipal(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
//        System.out.println("MyPrincipal.getName...");
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPrincipal that = (MyPrincipal) o;

        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
