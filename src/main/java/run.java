import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class run {
    public static void main(String[] args)  {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("andriudytko@gmail.com", "ascxhvyglwmflxmw");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("andriudo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("andriudytko@gmail.com"));
            message.setSubject("Mail Subject");

            String msg = "This is my first email using JavaMailer";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
