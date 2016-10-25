package lt.arminai.reactive.eventdriven.emailservice;

import java.util.List;

/**
 * Created by vytautas on 2016-10-25.
 */
public interface EmailService {
    void sendEmail(List<String> recipientList, String fromEmail, String subject, String text);
}
