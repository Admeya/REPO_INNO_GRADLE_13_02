package customLayouts;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ирина on 14.02.2017.
 */
public class CustomAppender extends AppenderSkeleton {
    private String topic = "Information about errors";
    private static String text = "";

    @Override
    protected void append(LoggingEvent event) {
        CustomLogLayout layout = (CustomLogLayout) this.getLayout();
        text = text + "\n" + (String) event.getMessage();
    }

    @Override
    public void close() {
        Properties property = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = property.getProperty("username");
        String password = property.getProperty("password");
        String smtpHost = property.getProperty("smtp.host");
        String smtpPort = property.getProperty("smtp.port");
        String sendTo = property.getProperty("send.to");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(sendTo));
            message.setSubject(topic);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean requiresLayout() {
        return false;
    }
}
