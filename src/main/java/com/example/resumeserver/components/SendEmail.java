package com.example.resumeserver.components;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
    private String toEmail;
    private final Properties properties;
    private Session session;
    private String login;

    public SendEmail() {
        this.properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("smtp.ssl.protocols", "TLSv1.2");
    }

    //    andriudytko@gmail.com
    //    ascxhvyglwmflxmw
    public SendEmail singIn(String login, String password){
        this.login = login;
        this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
        return SendEmail.this;
    }

    public SendEmail to(String toEmail){
        this.toEmail = toEmail;
        return SendEmail.this;
    }

    public boolean send(String email, String name, String userMesssage){
        try {
            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.login));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.toEmail));
            message.setSubject(email + ": " + name);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(userMesssage, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
