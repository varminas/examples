package lt.arminai.reactive.eventdriven.emailservice;

import java.util.List;

/**
 * Created by vytautas on 2016-10-25.
 */
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(List<String> recipientList, String fromEmail, String subject, String text) {
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("Sending email");
        System.out.println("------------------------------");

        System.out.println("To: ");

        recipientList.forEach(recipient -> {
            System.out.print(recipient);
            System.out.print("; ");
        });

        System.out.println();

        System.out.println("From: " + fromEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Text: " + text);
        System.out.println();
        System.out.println(text);
        System.out.println();

        System.out.println("------------------------------");
    }
}
