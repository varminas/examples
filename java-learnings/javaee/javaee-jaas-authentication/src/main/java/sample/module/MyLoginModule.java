package sample.module;

import sample.principal.MyPrincipal;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vytautas on 17.6.3.
 */
public class MyLoginModule implements LoginModule {
    public static final String[][] TEST_USERS = {{"user1", "pwd1"}, {"user2", "pwd2"}, {"user3", "pwd3"}};

    private Subject subject;
    private CallbackHandler callbackHandler;
    private MyPrincipal myPrincipal;

    // the authentication status
    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    // username and password
    private String username;
    private char[] password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;

        System.out.println("MyLoginModule.initializing...");
    }

    @Override
    public boolean login() throws LoginException {
        System.out.println("MyLoginModule.login...");
        boolean succeeded = false;

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username: ");
        callbacks[1] = new PasswordCallback("Password: ", false);
        try {
            callbackHandler.handle(callbacks);

            String username = ((NameCallback) callbacks[0]).getName();
            String password = new String(((PasswordCallback) callbacks[1]).getPassword());

            int i;
            for (i = 0; i < TEST_USERS.length; i++) {
                if (TEST_USERS[i][0].equals(username) && TEST_USERS[i][1].equals(password)) {
                    myPrincipal = new MyPrincipal(username);

                    System.out.println("Authentication succeeded...");

                    succeeded = true;
                    break;
                }
            }

            if (!succeeded) {
                throw new FailedLoginException("Authentication failed");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            System.out.println(e.getMessage());
        }

        return succeeded;
    }

    @Override
    public boolean commit() throws LoginException {
        boolean flag = false;
        System.out.println("MyLoginModule.commit...");
        if (subject != null && !subject.getPrincipals().contains(myPrincipal)) {
            subject.getPrincipals().add(myPrincipal);
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean abort() throws LoginException {
        System.out.println("MyLoginModule.abort...");

        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceeded == false) {
            // login succeeded but overall authentication failed
            succeeded = false;
            username = null;
            if (password != null) {
                for (int i = 0; i < password.length; i++)
                    password[i] = ' ';
                password = null;
            }
            myPrincipal = null;
        } else {
            // overall authentication succeeded and commit succeeded,
            // but someone else's commit failed
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        System.out.println("MyLoginModule.logout...");

        subject.getPrincipals().remove(myPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        username = null;
        if (password != null) {
            for (int i = 0; i < password.length; i++)
                password[i] = ' ';
            password = null;
        }
        myPrincipal = null;
        return true;
    }
}
