package pl.bb.broker.settings;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 13.06.14
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class MailSettings {
    public static final String EMAIL_SESSION_JNDI_PATH = "java:jboss/mail/broker";

    private static final String username = "broker.notifications@gmail.com";
    private static final String password = "broker123";

    private static final Properties props = new Properties();

    static {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public static Session openSession() {
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

}
