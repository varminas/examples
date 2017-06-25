package sample;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivilegedAction;

/**
 * Subject - the user beeing authenticated
 * LoginModule populates Subject with the Principal representing the user (if login is successful), i.e. Principal is added to the Subject
 * Principle - igaliojimas
 *
 * CallbackHandler is used to obtain username and password for LoginModule
 * LoginContext forwards the CallbackHandler directly to the underlying LoginModules.
 *
 * Created by vytautas on 17.6.2.
 */
public class SampleAcn {
    private static final int ATTEMPT_COUNT = 3;
    private static final String CONFIG_FILE_NAME = "sample_jaas.config";
    private static final String POLICY_FILE_NAME = "sampleacn.policy";
    private static final String CONFIG_NAME = "SampleConfig";

    public static void main(String[] args) {
        SampleAcn sampleAcn = new SampleAcn();

        sampleAcn.setup();

        LoginContext loginContext = sampleAcn.createLoginContext();

        sampleAcn.login(loginContext);
    }

    private void setup() {
        SecurityManager sm = new SecurityManager();

        System.setProperty("java.security.auth.login.config", getConfigFilePath());
        System.setProperty("java.security.policy", getPolicyFilePath());

        System.out.println(System.getProperty("java.security.auth.login.config"));
//        System.setSecurityManager(sm);
    }

    private String getConfigFilePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CONFIG_FILE_NAME).getFile());

        return file.getPath();
    }

    private String getPolicyFilePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(POLICY_FILE_NAME).getFile());

        return file.getPath();
    }

    private LoginContext createLoginContext() {
        LoginContext loginContext = null;

        try {
            loginContext = new LoginContext(CONFIG_NAME, new MyCallbackHandler());
        } catch (LoginException | SecurityException le) {
            System.err.println("Cannot create login context. " + le.getMessage());
            System.exit(-1);
        }
        return loginContext;
    }

    private void login(LoginContext loginContext) {
        int i;
        for (i = 0; i < ATTEMPT_COUNT; i++) {
            try {
                loginContext.login();

                performActions(loginContext);
            } catch (LoginException le) {
                System.err.println("Authentication failed. " + le.getMessage());
                try {
                    Thread.currentThread().sleep(3000);
                } catch (Exception e) {
                    // ignore
                }
            }
        }

        ensureAttemptLimitNotReached(i);

        System.out.println("Authentication succeeded!");
    }

    private static void ensureAttemptLimitNotReached(int i) {
        if (i == ATTEMPT_COUNT) {
            System.out.println("Sorry. Too many times");
            System.exit(-1);
        }
    }

    private void performActions(LoginContext loginContext) {
        boolean allowedToPerformAction = true;
        while (allowedToPerformAction) {
            try {
                allowedToPerformAction = performAction(loginContext);
            } catch (IOException | LoginException e) {
                System.out.println("Error while performing action. " + e.getMessage());
            }
        }
    }

    private static boolean performAction(LoginContext loginContext) throws IOException, LoginException {
        boolean allowToPerformAction = true;

        System.out.println("Please specify action to take (ACTION1, ACTION2, LOGOUT)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            switch (Action.valueOf(br.readLine())) {
                case LOGOUT:
                    loginContext.logout();
                    System.out.println("You are logged out");
                    allowToPerformAction = false;
                    break;

                case ACTION1:
                    PrivilegedAction<Object> privilegedAction1 = () -> {
                        System.out.print("ACTION1 was performed ");
                        return null;
                    };

                    Subject.doAs(loginContext.getSubject(), privilegedAction1);
                    System.out.println(" by " + loginContext.getSubject().getPrincipals().iterator().next().getName());
                    break;

                case ACTION2:
                    PrivilegedAction<Object> privilegedAction2 = () -> {
                        System.out.print("ACTION2 was performed ");
                        return null;
                    };

                    Subject.doAs(loginContext.getSubject(), privilegedAction2);
                    System.out.println(" by " + loginContext.getSubject().getPrincipals().iterator().next().getName());
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("invalid action " + e.getMessage());
        }

        return allowToPerformAction;
    }

    public enum Action {
        ACTION1,
        ACTION2,
        LOGOUT;
    }
}
