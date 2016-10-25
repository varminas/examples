package lt.arminai.reactive.eventdriven;

import lt.arminai.reactive.eventdriven.emailmonitor.EmailMonitor;
import lt.arminai.reactive.eventdriven.emailservice.EmailService;
import lt.arminai.reactive.eventdriven.emailservice.EmailServiceImpl;
import lt.arminai.reactive.eventdriven.userservice.UserService;
import lt.arminai.reactive.eventdriven.userservice.UserServiceImpl;

/**
 * Created by vytautas on 2016-10-25.
 */
public class EventDrivenExample {
    public static void main(String[] args) {
        try {
            EmailService emailService = new EmailServiceImpl();

            UserService userService = new UserServiceImpl();

            new EmailMonitor(emailService, userService);

            userService.addUser("Vytautas", "test@gmail.com");

            Thread.sleep(2000);

            System.exit(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
