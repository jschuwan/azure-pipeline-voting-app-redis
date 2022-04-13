package info.schuwan.api2.controllers;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailController {



        public SendMailController(String mySubject, String emailBody, String email) {


            String to = email;          //After testing make the to = email
            String from = "blackjaak009@gmail.com"; //After testing make the from = a gmail email setup for notifications
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();


            // Setup mail server
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            // Get the Session object.// and pass username and password
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "rqcbebauqrvipdly");
                }

            });

            // Used to debug SMTP issues
            session.setDebug(true);

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));                // Sent From:
                message.addRecipient(Message.RecipientType.TO, new InternetAddress("jschuwan@me.com"));             // Sent To:
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(mySubject);                    // Subject: header field
                message.setText(emailBody);                 //  message

                System.out.println("sending...");
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }

        }

    }