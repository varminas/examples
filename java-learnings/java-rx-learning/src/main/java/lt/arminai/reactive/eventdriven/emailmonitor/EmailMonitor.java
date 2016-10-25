package lt.arminai.reactive.eventdriven.emailmonitor;

import lt.arminai.reactive.eventdriven.emailservice.EmailService;
import lt.arminai.reactive.eventdriven.userservice.UserEvent;
import lt.arminai.reactive.eventdriven.userservice.UserService;

import java.util.ArrayList;

/**
 * Created by vytautas on 2016-10-25.
 */
public class EmailMonitor {
    private final EmailService emailService;

    public EmailMonitor(EmailService emailService, UserService userService) {
        this.emailService = emailService;

        userService.subscribeToUserEvents(this::handleUserEvent);
    }

    private void handleUserEvent(UserEvent t) {
        System.out.println("EmailMonitorServiceImpl::handleUserEvent - " + Thread.currentThread().getName());

        ArrayList<String> recipientList = new ArrayList<>();
        recipientList.add(t.getEmailAddress());
        String text = "Hi " + t.getUsername() + ", Welcome to courses!!";
        emailService.sendEmail(recipientList, "noreply@test.se", "Title: Welcome to courses!", text);
    }
}
