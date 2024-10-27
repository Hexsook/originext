package hexsook.originext;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Mail operations.
 */
public class MailUtil {

    /**
     * Sends mail using html format.
     */
    public static void sendHtmlMail(String smtpHost, int smtpPort, String senderAddress,
                                    String senderPassword, String senderNickname, String targetAddress,
                                    String subject, String htmlContent) throws Exception {
        // Configure mail server
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        // Create session
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAddress, senderPassword);
            }
        });

        // Create mail message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderAddress, senderNickname));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetAddress));
        message.setSubject(subject);
        message.setContent(htmlContent, "text/html; charset=utf-8");

        Transport.send(message);
    }
}
